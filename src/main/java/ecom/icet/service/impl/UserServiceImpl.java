package ecom.icet.service.impl;

import ecom.icet.model.dto.UserDto;
import ecom.icet.model.entity.User;
import ecom.icet.repository.UserRepository;
import ecom.icet.service.AiCoachService;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AiCoachService aiCoachService;

    @Override
    public void registerUser(UserDto userDto) {
        // DTO එක Entity එකකට හරවනවා
        User user = modelMapper.map(userDto, User.class);
        // Database එකේ සේව් කරනවා
        userRepository.save(user);
    }

    @Override
    public String getAiAdviceForUser(String email) {
        // 1. Repository එකෙන් එන්නේ Optional එකක්
        Optional<User> userOptional = userRepository.findByEmail(email);

        // 2. යූසර් කෙනෙක් ඉන්නවද කියලා චෙක් කරනවා
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // ඇතුළේ ඉන්න User object එක ගන්නවා

            String prompt = "I am a health coach. My client is " + user.getName() +
                    ". Weight: " + user.getWeight() + "kg, Height: " + user.getHeight() + "cm. " +
                    "Give a short health tip based on this data.";

            return aiCoachService.getAiAdvice(prompt);
        }

        return "User not found with email: " + email;
    }
}
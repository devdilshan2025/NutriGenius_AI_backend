package ecom.icet.service.impl;

import ecom.icet.model.entity.User;
import ecom.icet.repository.UserRepository;
import ecom.icet.service.AiCoachService;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AiCoachService aiCoachService;

    // මචං, registerUser සහ loginUser මෙතනින් අයින් කළා.
    // මොකද ඒවා දැන් AuthServiceImpl එකේ තියෙන්නේ.

    @Override
    public String getAiAdviceForUser(String email) {
        // 1. Database එකෙන් Userව හොයාගන්නවා
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 2. AI එකට යවන Prompt එක හදනවා
            String prompt = "I am a health coach. My client is " + user.getName() +
                    ". Weight: " + user.getWeight() + "kg, Height: " + user.getHeight() + "cm. " +
                    "Give a short, professional health tip based on this data.";

            // 3. AI Service එකට කතා කරලා advice එක ගන්නවා
            return aiCoachService.getAiAdvice(prompt);
        }

        return "User not found with email: " + email;
    }
}
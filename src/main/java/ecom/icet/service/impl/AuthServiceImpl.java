package ecom.icet.service.impl;

import ecom.icet.model.dto.UserDto;
import ecom.icet.model.entity.User;
import ecom.icet.repository.UserRepository;
import ecom.icet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    @Override
    public boolean loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        }
        return false;
    }
}
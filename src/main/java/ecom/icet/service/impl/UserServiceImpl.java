package ecom.icet.service.impl;

import ecom.icet.model.dto.UserDto;
import ecom.icet.model.entity.User;
import ecom.icet.repository.UserRepository;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void registerUser(UserDto userDto) {
        // DTO එක Entity එකකට හරවනවා
        User user = modelMapper.map(userDto, User.class);
        // Database එකේ සේව් කරනවා
        userRepository.save(user);
    }
}
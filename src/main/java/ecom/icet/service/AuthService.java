package ecom.icet.service;

import ecom.icet.model.dto.UserDto;

public interface AuthService {
    void registerUser(UserDto userDto);
    boolean loginUser(String email, String password);
}
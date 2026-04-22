package ecom.icet.controller;

import ecom.icet.model.dto.UserDto;
import ecom.icet.service.AuthService; // මචං, මෙතන AuthService import වෙන්න ඕනේ
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    // පරණ UserService වෙනුවට අලුත් AuthService එක මෙතනට දාන්න
    private final AuthService authService;

    // 1. Registration Endpoint
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
        // මෙතනත් authService පාවිච්චි කරන්න
        authService.registerUser(userDto);
        return "User Registered Successfully!";
    }

    // 2. Login Endpoint
    @PostMapping("/login")
    public String login(@RequestBody UserDto loginDto) {
        // මෙතනත් authService පාවිච්චි කරන්න
        boolean isAuthenticated = authService.loginUser(loginDto.getEmail(), loginDto.getPassword());

        if (isAuthenticated) {
            return "Login Successful!";
        } else {
            return "Invalid Email or Password!";
        }
    }
}
package ecom.icet.controller;

import ecom.icet.model.dto.UserDto;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor // Constructor injection එක ලේසි කරන්න ලොම්බොක් පාවිච්චි කරනවා
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return "User Registered Successfully!";
    }

    @GetMapping("/personalized-advice")
    public String getPersonalizedAdvice(@RequestParam String email) {
        return userService.getAiAdviceForUser(email);
    }
}
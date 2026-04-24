package ecom.icet.controller;

import ecom.icet.model.dto.UserDto;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    /**
     * Dashboard එකේ Weight, Height, Age පෙන්වීමට දත්ත ලබාගැනීම
     * GET: http://localhost:8080/api/user/details?email=test@example.com
     */
    @GetMapping("/details")
    public UserDto getUserDetails(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
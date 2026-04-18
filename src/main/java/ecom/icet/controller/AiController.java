package ecom.icet.controller;

import ecom.icet.service.AiCoachService;
import ecom.icet.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class AiController {

    private final AiCoachService aiCoachService;
    private final UserService userService; // 1. UserService එක මෙතනට එකතු කරන්න

    // 2. Constructor එකට UserService එකත් ඇතුළත් කරන්න
    public AiController(AiCoachService aiCoachService, UserService userService) {
        this.aiCoachService = aiCoachService;
        this.userService = userService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return aiCoachService.getAiAdvice(message);
    }

    // 3. Personalized Advice ලබාගන්නා අලුත් Endpoint එක
    @GetMapping("/personalized-advice")
    public String getPersonalizedAdvice(@RequestParam String email) {
        // මේකෙන් වෙන්නේ UserService එක හරහා Database එකේ ඉන්න යූසර්ව හොයලා AI advice එක දෙන එක
        return userService.getAiAdviceForUser(email);
    }
}
package ecom.icet.controller;

import ecom.icet.service.AiCoachService;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AiController {

    private final AiCoachService aiCoachService;
    private final UserService userService;




    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return aiCoachService.getAiAdvice(message);
    }

    /**
     * යූසර්ගේ සෞඛ්‍ය දත්ත මත පදනම්ව AI උපදෙස් ලබාගැනීම
     * GET: http://localhost:8080/api/ai/personalized-advice?email=test@example.com
     */
    @GetMapping("/personalized-advice")
    public String getPersonalizedAdvice(@RequestParam String email) {
        return userService.getAiAdviceForUser(email);
    }
}
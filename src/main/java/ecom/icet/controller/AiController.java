package ecom.icet.controller;

import ecom.icet.service.AiCoachService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin // Frontend එකෙන් (React) කෝල් කරනවා නම් මේක දාන්න
public class AiController {

    private final AiCoachService aiCoachService;

    // Constructor Injection
    public AiController(AiCoachService aiCoachService) {
        this.aiCoachService = aiCoachService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        // Service එක හරහා Gemini AI එකෙන් උත්තරය අරන් දෙනවා
        return aiCoachService.getAiAdvice(message);
    }
}
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @GetMapping("/personalized-advice")
    public String getPersonalizedAdvice(@RequestParam String email) {
        return userService.getAiAdviceForUser(email);
    }
}
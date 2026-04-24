package ecom.icet.service.impl;

import ecom.icet.model.dto.UserDto;
import ecom.icet.model.entity.User;
import ecom.icet.repository.UserRepository;
import ecom.icet.service.UserService;
import ecom.icet.service.AiCoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AiCoachService aiCoachService;

    @Override
    public UserDto getUserByEmail(String email) {
        // මෙතන ඔයා හරි: .orElse(null) පාවිච්චි කරලා තියෙනවා
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setWeight(user.getWeight());
        dto.setHeight(user.getHeight());
        dto.setAge(user.getAge());

        return dto;
    }

    @Override
    public String getAiAdviceForUser(String email) {
        // මෙන්න මෙතනටයි .orElse(null) එක ඕන වුණේ!
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return "User not found!";

        // දැන් රතු ඉර මැකෙයි, මොකද 'user' දැන් Optional එකක් නෙවෙයි
        String customPrompt = String.format(
                "My weight is %s kg, height is %s cm and age is %d. Give me a brief health tip.",
                user.getWeight(), user.getHeight(), user.getAge()
        );

        return aiCoachService.getAiAdvice(customPrompt);
    }
}
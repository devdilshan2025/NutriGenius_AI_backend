package ecom.icet.service.impl;

import org.springframework.beans.factory.annotation.Value;
import ecom.icet.service.AiCoachService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class AiCoachServiceImpl implements AiCoachService {

    @Value("${ai.google.gemini.api-key}")
    private String apiKey;

    // වැදගත්: Browser එකේ තිබුණු විදිහටම gemini-2.5-flash ලෙස update කරන්න
    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=";

    @Override
    public String getAiAdvice(String userPrompt) {
        // 1. SSL/TLS ප්‍රශ්න මගහරින්න මේ පේළිය මෙතනට දාන්න
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", userPrompt)))
                )
        );

        try {
            // 2. API call එක යනවා
            Map<String, Object> response = restTemplate.postForObject(GEMINI_API_URL + apiKey, requestBody, Map.class);

            if (response != null && response.containsKey("candidates")) {
                List candidates = (List) response.get("candidates");
                Map firstCandidate = (Map) candidates.get(0);
                Map content = (Map) firstCandidate.get("content");
                List parts = (List) content.get("parts");
                Map firstPart = (Map) parts.get(0);

                return firstPart.get("text").toString();
            }
            return "AI returned an empty response.";

        } catch (Exception e) {
            // මෙතනදී තමයි I/O Error එක අහු වෙන්නේ
            return "AI Error: " + e.getMessage();
        }
    }
}
package ecom.icet.service;

public interface AiCoachService {
    /**
     * යූසර් එවන පණිවිඩය (Prompt) අනුව AI උපදෙස් ලබා ගැනීම.
     * @param userPrompt - යූසර් අසන ප්‍රශ්නය හෝ විස්තරය
     * @return AI එකෙන් ලැබෙන පිළිතුර (String)
     */
    String getAiAdvice(String userPrompt);
}
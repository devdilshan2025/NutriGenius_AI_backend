package ecom.icet.service;

public interface AiCoachService {
    // නිකම්ම චැට් කරන එකට (Gemini call එක)
    String getAiAdvice(String userPrompt);
}
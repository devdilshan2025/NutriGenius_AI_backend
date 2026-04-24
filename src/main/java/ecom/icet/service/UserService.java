package ecom.icet.service;

import ecom.icet.model.dto.UserDto;

public interface UserService {
    // DB එකෙන් දත්ත ටික අරන් Angular එකට දෙන්න
    UserDto getUserByEmail(String email);

    // DB එකේ උස/බර අරන් AI ඇඩ්වයිස් එකක් හදන්න
    String getAiAdviceForUser(String email);
}
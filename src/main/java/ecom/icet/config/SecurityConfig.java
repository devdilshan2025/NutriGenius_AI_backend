package ecom.icet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF disable කරනවා (API එකක් නිසා මේක ඕනේ)
                .csrf(AbstractHttpConfigurer::disable)

                // Endpoints වලට අවසර දෙන ආකාරය
                .authorizeHttpRequests(auth -> auth
                        // AI Chat එකට ඕනෑම කෙනෙකුට අවසර දෙනවා (Public)
                        .requestMatchers("/api/ai/**").permitAll()

                        // ඉතිරි ඕනෑම request එකකට Login වෙලා ඉන්න ඕනේ
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
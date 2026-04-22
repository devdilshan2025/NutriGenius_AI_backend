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
                // CSRF disable කරනවා - API එකක් Postman වලින් test කරන්න මේක අනිවාර්යයි
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        // 1. අලුත් AuthController එකේ පාරවල් (Login/Register) වලට ඕනෑම කෙනෙකුට අවසර දෙනවා
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. AI Chat එකට සහ පරණ AI පාරවල් වලට අවසර දෙනවා
                        .requestMatchers("/api/ai/**").permitAll()

                        // 3. User පාරවල් (Advice, etc.) වලටත් දැනට අවසර දෙමු (පස්සේ මේක වෙනස් කරමු)
                        .requestMatchers("/api/user/**").permitAll()

                        // ඉතිරි ඕනෑම request එකකට විතරක් Login වෙලා ඉන්න ඕනේ
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
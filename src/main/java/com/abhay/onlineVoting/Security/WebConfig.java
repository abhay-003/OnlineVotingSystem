package com.abhay.onlineVoting.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig {
	
	private final CustomeUserDetail customeUserDetails;  // 
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Completely shuts off CSRF
            .authorizeHttpRequests(auth -> auth.requestMatchers("/api/admin/**", "/api/result/**").hasRole("ADMIN")
            		.requestMatchers("/api/voting/**").hasRole("VOTER")
            		.requestMatchers("/api/result/**").hasRole("CANDIDATE")
            		.requestMatchers("/api/register/**").permitAll()
            		.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        // Allows all testing routes
        return http.build();
    }


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
	}
}

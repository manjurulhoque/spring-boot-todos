package com.example.todos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String loginPage = "/login";
        String logoutPage = "/logout";
        String registrationPage = "/registration";

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers(loginPage).permitAll()
                        .requestMatchers(registrationPage).permitAll()
                        .requestMatchers("/**").permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin
                        .loginPage(loginPage)
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/todos", true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                        .logoutSuccessUrl(loginPage)
                )
                .exceptionHandling(Customizer.withDefaults());

        return http.build();
    }
}

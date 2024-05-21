package com.example.cv.securitys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class securityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                            .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                            .requestMatchers("/public/**","/templates/**","/static/**","/img/**").permitAll()
                            .anyRequest().authenticated();
                }).formLogin(formLogin -> formLogin
                                .loginPage("/public/signin")
                                .loginProcessingUrl("/public/si1")
                                .defaultSuccessUrl("/public/sucsessfull")
                );
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails aadmin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .authorities("ROLE_ADMIN")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .authorities("ROLE_USER")
//                .build();
//        UserDetails nt = User.builder()
//                .username("nt")
//                .password(passwordEncoder().encode("nt")).build();
//        return  new InMemoryUserDetailsManager(aadmin,user,nt);
//    }
}

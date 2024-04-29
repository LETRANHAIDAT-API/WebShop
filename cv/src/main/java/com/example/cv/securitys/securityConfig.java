package com.example.cv.securitys;

import com.example.cv.Model.users;
import com.example.cv.Services.customUserDetailsServices;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class securityConfig {
    private customUserDetailsServices customUserDetailsServices;
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
                            .requestMatchers("/public/**","/templates/**","/resources/**","/static/**").permitAll()
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

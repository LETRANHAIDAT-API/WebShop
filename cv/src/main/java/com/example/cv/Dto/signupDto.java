package com.example.cv.Dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class signupDto {
    private String name;
    private String email;
    @Email(message = "emai da ton tai")
    private String password;
}

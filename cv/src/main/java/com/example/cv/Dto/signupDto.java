package com.example.cv.Dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class signupDto {
    private String name;
    private String email;
    private String password;
}

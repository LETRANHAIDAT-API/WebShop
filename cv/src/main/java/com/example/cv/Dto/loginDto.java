package com.example.cv.Dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class loginDto {
    private int nameOremail;
    private String password;
}

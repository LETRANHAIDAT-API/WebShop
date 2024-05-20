package com.example.cv.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class loginDto {
    @NotEmpty
    @Email(regexp = "",message = "pattern error")
    private int nameOremail;
    @NotEmpty
    @Min(value = 8,message = "min 8")
    private String password;
}

package org.happybaras.taller3.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String identifier;
    @NotBlank
    private String password;
}

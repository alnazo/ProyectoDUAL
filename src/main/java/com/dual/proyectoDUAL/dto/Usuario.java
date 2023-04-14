package com.dual.proyectoDUAL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    private String username;
    private String password;
    private String email;
    private boolean admin;
}

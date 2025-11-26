package com.perfuland.perfulandia.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String userName; // <-- CORREGIDO de user_name
    private String password;

}
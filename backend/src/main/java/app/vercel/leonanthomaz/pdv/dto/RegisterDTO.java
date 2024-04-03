package app.vercel.leonanthomaz.pdv.dto;

import app.vercel.leonanthomaz.pdv.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para registrar um novo usuário.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    /**
     * O nome do usuário a ser registrado.
     */
    private String name;

    /**
     * A matrícula do usuário a ser registrada.
     */
    private String registration;

    /**
     * O email do usuário a ser registrado.
     */
    private String email;

    /**
     * A senha do usuário a ser registrada.
     */
    private String password;

    /**
     * O papel (role) do usuário a ser registrado.
     */
    private UserRole role;
}

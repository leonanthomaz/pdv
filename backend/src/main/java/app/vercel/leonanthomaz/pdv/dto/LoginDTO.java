package app.vercel.leonanthomaz.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para informações de login.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    /**
     * A matrícula do usuário para login.
     */
    private String registration;

    /**
     * A senha do usuário para login.
     */
    private String password;
}

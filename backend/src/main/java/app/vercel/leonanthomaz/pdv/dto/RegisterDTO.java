package app.vercel.leonanthomaz.pdv.dto;

import app.vercel.leonanthomaz.pdv.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String name;
    private String registration;
    private String email;
    private String password;
    private UserRole role;
}

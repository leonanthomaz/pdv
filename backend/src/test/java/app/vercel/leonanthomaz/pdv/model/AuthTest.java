package app.vercel.leonanthomaz.pdv.model;

import app.vercel.leonanthomaz.pdv.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
public class AuthTest {
    public static final UserDetails USER_VALID = Auth.builder()
            .id(1L)
            .name("Leonan")
            .registration("9876543210")
            .email("leonan.thomaz@gmail.com")
            .password(passwordEncoder().encode("123"))
            .role(UserRole.valueOf("ADMIN"))
            .build();
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



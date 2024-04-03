package app.vercel.leonanthomaz.pdv.model;

import app.vercel.leonanthomaz.pdv.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Modelo que representa um usuário no sistema.
 */
@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do usuário.
     */
    private String name;

    /**
     * Registro do usuário.
     */
    private String registration;

    /**
     * Email do usuário.
     */
    private String email;

    /**
     * Senha do usuário (criptografada).
     */
    private String password;

    /**
     * Papel/Permissão do usuário no sistema.
     */
    private UserRole role;

    /**
     * Construtor padrão.
     */
    public User(String name, String registration, String email, String password, UserRole role) {
        this.name = name;
        this.registration = registration;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Retorna as autorizações (papéis) do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("USER"));
        }
    }

    /**
     * Retorna o nome de usuário (que é o número de registro neste caso).
     */
    @Override
    public String getUsername() {
        return this.registration;
    }

    /**
     * Verifica se a conta do usuário está expirada (sempre retorna verdadeiro neste caso).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Verifica se a conta do usuário está bloqueada (sempre retorna verdadeiro neste caso).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Verifica se as credenciais do usuário estão expiradas (sempre retorna verdadeiro neste caso).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Verifica se o usuário está habilitado (sempre retorna verdadeiro neste caso).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}


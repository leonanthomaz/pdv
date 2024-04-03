package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repositório para operações relacionadas à entidade User.
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Busca um usuário pelo número de matrícula.
     *
     * @param registration O número de matrícula do usuário a ser encontrado.
     * @return O UserDetails correspondente ao usuário encontrado.
     */
    UserDetails findByRegistration(String registration);
}

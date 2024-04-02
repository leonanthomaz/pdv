package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByRegistration(String registration);
}

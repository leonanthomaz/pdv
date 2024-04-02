package app.vercel.leonanthomaz.pdv.controller;

import app.vercel.leonanthomaz.pdv.model.User;
import app.vercel.leonanthomaz.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{registration}")
    public ResponseEntity<User> findUserWithRegistration(@PathVariable String registration) {
        User user = (User) userRepository.findByRegistration(registration);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

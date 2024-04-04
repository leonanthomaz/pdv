package app.vercel.leonanthomaz.pdv.controller;

import app.vercel.leonanthomaz.pdv.model.Auth;
import app.vercel.leonanthomaz.pdv.repository.AuthRepository;
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
    private AuthRepository authRepository;

    @GetMapping("/{registration}")
    public ResponseEntity<Auth> findUserWithRegistration(@PathVariable String registration) {
        Auth auth = (Auth) authRepository.findByRegistration(registration);
        if (auth != null) {
            return ResponseEntity.ok(auth);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

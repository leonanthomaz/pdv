package app.vercel.leonanthomaz.pdv.controller;

import app.vercel.leonanthomaz.pdv.config.auth.dto.TokenDTO;
import app.vercel.leonanthomaz.pdv.config.auth.token.TokenService;
import app.vercel.leonanthomaz.pdv.dto.LoginDTO;
import app.vercel.leonanthomaz.pdv.dto.RegisterDTO;
import app.vercel.leonanthomaz.pdv.model.User;
import app.vercel.leonanthomaz.pdv.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Log4j2
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getRegistration(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.createToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByRegistration(data.getRegistration()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getName(), data.getRegistration(), data.getEmail(), encryptedPassword, data.getRole());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity getUserDetails(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        UserDetails userDetails = tokenService.getUserDetailsFromToken(token);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido!");
        }
    }
}

package app.vercel.leonanthomaz.pdv.service;

import app.vercel.leonanthomaz.pdv.model.User;
import app.vercel.leonanthomaz.pdv.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço para criação, validação e obtenção de informações a partir de tokens JWT.
 */
@Service
@Log4j2
public class TokenService {

    private String secret = "MinhaPalavra";

    @Autowired
    private UserRepository userRepository;

    /**
     * Cria um token JWT para o usuário.
     *
     * @param user O usuário para o qual o token será criado.
     * @return O token JWT gerado.
     */
    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("login")
                    .withSubject(user.getRegistration())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("Erro na geração do Token para o usuário: {}", exception.getMessage());
            throw new RuntimeException("Erro na geração do Token para o usuário.", exception);
        }
    }

    /**
     * Valida um token JWT e retorna o registro do usuário associado a ele.
     *
     * @param token O token JWT a ser validado.
     * @return O registro do usuário associado ao token.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.error("Erro na validação do Token: {}", exception.getMessage());
            return "";
        }
    }

    /**
     * Obtém os detalhes do usuário a partir de um token JWT.
     *
     * @param token O token JWT contendo as informações do usuário.
     * @return Os detalhes do usuário.
     */
    public User getUserDetailsFromToken(String token) {
        try {
            String registration = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("login")
                    .build()
                    .verify(token)
                    .getSubject();
            return (User) userRepository.findByRegistration(registration);
        } catch (JWTVerificationException exception) {
            log.error("Erro na validação do Token: {}", exception.getMessage());
            return null;
        }
    }

    /**
     * Gera a data de expiração para o token JWT (2 horas a partir do momento atual).
     *
     * @return A data de expiração do token.
     */
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
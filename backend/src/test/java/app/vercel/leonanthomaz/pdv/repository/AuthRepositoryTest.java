package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.Auth;
import app.vercel.leonanthomaz.pdv.model.AuthTest;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Testes para o repositorio authRepository.")
@Log4j2
class AuthRepositoryTest {

    @Autowired
    private AuthRepository authRepository;

    @Test
    @DisplayName("Retorna um usuário com dados válidos.")
    void createUser_WhenSuccess_ReturnUser() {
        Auth authToBeSaved = (Auth) AuthTest.USER_VALID;
        Auth authSaved = authRepository.save(authToBeSaved);

        Assertions.assertThat(authSaved).isNotNull();
        Assertions.assertThat(authSaved).isEqualTo(AuthTest.USER_VALID);
    }
}
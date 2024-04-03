package app.vercel.leonanthomaz.pdv.controller;

import app.vercel.leonanthomaz.pdv.service.TokenService;
import app.vercel.leonanthomaz.pdv.dto.LoginDTO;
import app.vercel.leonanthomaz.pdv.dto.RegisterDTO;
import app.vercel.leonanthomaz.pdv.model.User;
import app.vercel.leonanthomaz.pdv.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("username", "password");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.createToken(any())).thenReturn("dummy_token");

        // Act
        ResponseEntity responseEntity = authController.login(loginDTO);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testRegister() {
        // Arrange
        RegisterDTO registerDTO = new RegisterDTO("John Doe", "username", "john@example.com", "password", "ROLE_USER");
        when(userRepository.findByRegistration(anyString())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(new User());

        // Act
        ResponseEntity responseEntity = authController.register(registerDTO);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetUserDetails() {
        // Arrange
        String authorizationHeader = "Bearer dummy_token";
        UserDetails userDetails = mock(UserDetails.class);
        when(tokenService.getUserDetailsFromToken(anyString())).thenReturn((User) userDetails);

        // Act
        ResponseEntity responseEntity = authController.getUserDetails(authorizationHeader);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
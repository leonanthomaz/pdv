package app.vercel.leonanthomaz.pdv.config.db;

import app.vercel.leonanthomaz.pdv.enums.UserRole;
import app.vercel.leonanthomaz.pdv.model.Auth;
import app.vercel.leonanthomaz.pdv.model.Product;
import app.vercel.leonanthomaz.pdv.repository.AuthRepository;
import app.vercel.leonanthomaz.pdv.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Database {

    @Bean
    public CommandLineRunner dataInitializer(AuthRepository authRepository, ProductRepository productRepository) {
        return args -> {
            // Criação de dois funcionários
            Auth funcionario1 = Auth.builder()
                    .name("Leonan")
                    .registration("12960")
                    .email("funcionario1@example.com")
                    .password(passwordEncoder().encode("123"))
                    .role(UserRole.ADMIN)
                    .build();

            Auth funcionario2 = Auth.builder()
                    .name("Thaiane")
                    .registration("14165")
                    .email("funcionario2@example.com")
                    .password(passwordEncoder().encode("456"))
                    .role(UserRole.USER)
                    .build();

            Product product1 = Product.builder()
                    .name("PRODUTO A")
                    .price(20.0)
                    .codeBar("78545454754547")
                    .build();
            Product product2 = Product.builder()
                    .name("PRODUTO B")
                    .price(10.0)
                    .codeBar("78411256442545")
                    .build();
            Product product3 = Product.builder()
                    .name("PRODUTO C")
                    .price(35.0)
                    .codeBar("78969641478963")
                    .build();

            // Salvando os funcionários no banco de dados
            authRepository.save(funcionario1);
            authRepository.save(funcionario2);

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
        };
    }

    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
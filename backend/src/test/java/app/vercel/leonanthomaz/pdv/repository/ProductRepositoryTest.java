package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o repositorio productRepository.")
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Retorna produto se dados foram válidos")
    public void createProduct_WhenSuccess_ReturnProduct() {
        Product product = Product.builder()
                .name("Product A")
                .price(10.0)
                .codeBar("1234567890")
                .build();
        Product savedProduct = productRepository.save(product);

        Assertions.assertThat(savedProduct.getId()).isNotNull();
        Assertions.assertThat(savedProduct.getName()).isEqualTo(product.getName());
        Assertions.assertThat(savedProduct.getCodeBar()).isEqualTo(product.getCodeBar());
        Assertions.assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    @DisplayName("Retorna produto por id")
    public void findByIdProduct_WhenSuccess_ReturnProduct() {
        Product product = Product.builder()
                .name("Product A")
                .price(10.0)
                .codeBar("1234567890")
                .build();
        Product savedProduct = productRepository.save(product);

        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());

        Assertions.assertThat(optionalProduct).isPresent();
        Product retrievedProduct = optionalProduct.get();
        Assertions.assertThat(retrievedProduct.getId()).isEqualTo(savedProduct.getId());
        Assertions.assertThat(retrievedProduct.getName()).isEqualTo(savedProduct.getName());
        Assertions.assertThat(retrievedProduct.getCodeBar()).isEqualTo(savedProduct.getCodeBar());
        Assertions.assertThat(retrievedProduct.getPrice()).isEqualTo(savedProduct.getPrice());
    }

    @Test
    @DisplayName("Retorna produto por código de barras")
    public void findByCodeBarProduct_WhenSuccess_ReturnProduct() {
        Product product = Product.builder()
                .name("Product A")
                .price(10.0)
                .codeBar("1234567890")
                .build();
        Product savedProduct = productRepository.save(product);

        Optional<Product> optionalProduct = productRepository.findByCodeBar(savedProduct.getCodeBar());

        Assertions.assertThat(optionalProduct).isPresent();
        Product retrievedProduct = optionalProduct.get();
        Assertions.assertThat(retrievedProduct.getId()).isEqualTo(savedProduct.getId());
        Assertions.assertThat(retrievedProduct.getName()).isEqualTo(savedProduct.getName());
        Assertions.assertThat(retrievedProduct.getCodeBar()).isEqualTo(savedProduct.getCodeBar());
        Assertions.assertThat(retrievedProduct.getPrice()).isEqualTo(savedProduct.getPrice());
    }
}
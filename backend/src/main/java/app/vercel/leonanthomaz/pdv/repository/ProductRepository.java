package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCodeBar(String codeBar);
}

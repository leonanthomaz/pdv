package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório para operações relacionadas à entidade Product.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca um produto pelo código de barras.
     *
     * @param codeBar O código de barras do produto a ser encontrado.
     * @return Um Optional contendo o produto encontrado, se existir.
     */
    Optional<Product> findByCodeBar(String codeBar);
}

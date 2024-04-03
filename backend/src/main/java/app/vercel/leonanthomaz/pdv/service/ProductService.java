package app.vercel.leonanthomaz.pdv.service;

import app.vercel.leonanthomaz.pdv.model.Product;
import app.vercel.leonanthomaz.pdv.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por lidar com operações relacionadas a produtos.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Recupera todos os produtos.
     *
     * @return Lista contendo todos os produtos.
     */
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    /**
     * Encontra um produto pelo ID.
     *
     * @param id O ID do produto a ser encontrado.
     * @return O produto encontrado.
     * @throws EntityNotFoundException Se o produto com o ID fornecido não for encontrado.
     */
    public Product findById(Long id){
        return Optional.ofNullable(productRepository.findById(id))
                .get()
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
    }

    /**
     * Encontra um produto pelo código de barras.
     *
     * @param codeBar O código de barras do produto a ser encontrado.
     * @return O produto encontrado.
     * @throws RuntimeException Se o produto com o código de barras fornecido não for encontrado.
     */
    public Product findByCodeBar(String codeBar){
        return productRepository.findByCodeBar(codeBar)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    /**
     * Salva um produto.
     *
     * @param product O produto a ser salvo.
     * @return O produto salvo.
     */
    public Product save(Product product){
        return productRepository.save(product);
    }
}

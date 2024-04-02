package app.vercel.leonanthomaz.pdv.service;

import app.vercel.leonanthomaz.pdv.model.Product;
import app.vercel.leonanthomaz.pdv.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product findById(Long id){
        return Optional.ofNullable(productRepository.findById(id)).get().orElseThrow(() -> new EntityNotFoundException("NÃO ENCONTRADO!"));
    }
    public Product findByCodeBar(String codeBar){
        return productRepository.findByCodeBar(codeBar)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
}

package app.vercel.leonanthomaz.pdv.controller;

import app.vercel.leonanthomaz.pdv.model.Product;
import app.vercel.leonanthomaz.pdv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }
    @PostMapping
    public ResponseEntity<Product> save(Product product){
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }
    @GetMapping("/{codeBar}") // Note a correção aqui, adicionando as chaves para indicar que é uma variável na URL
    public ResponseEntity<Product> findByCode(@PathVariable String codeBar){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCodeBar(codeBar));
    }
}

package app.vercel.leonanthomaz.pdv.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Modelo que representa um produto no sistema.
 */
@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do produto.
     */
    private String name;

    /**
     * Preço do produto.
     */
    private Double price;

    /**
     * Código de barras do produto.
     */
    private String codeBar;
}
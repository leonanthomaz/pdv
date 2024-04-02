package app.vercel.leonanthomaz.pdv.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String name;
    private Double price;
    private String codeBar;
}
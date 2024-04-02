package app.vercel.leonanthomaz.pdv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_cashier_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashierItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    @JsonIgnore
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashierItem that = (CashierItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Método para calcular o preço total do item
    public BigDecimal getTotalPrice() {
        if (quantity != null && price != null) {
            return price.multiply(BigDecimal.valueOf(quantity));
        } else {
            return BigDecimal.ZERO;
        }
    }
}
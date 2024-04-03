package app.vercel.leonanthomaz.pdv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Modelo que representa um item de venda em um caixa.
 */
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

    /**
     * O caixa ao qual este item está associado.
     */
    @ManyToOne
    @JoinColumn(name = "cashier_id")
    @JsonIgnore
    private Cashier cashier;

    /**
     * O produto vendido neste item.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * A quantidade do produto vendido neste item.
     */
    private Integer quantity;

    /**
     * O preço unitário do produto neste item.
     */
    private BigDecimal price;

    /**
     * O preço total deste item (quantidade * preço).
     */
    private BigDecimal totalPrice;

    /**
     * Verifica se dois itens de caixa são iguais com base no ID.
     * @param o O objeto a ser comparado.
     * @return true se os itens de caixa forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashierItem that = (CashierItem) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Retorna o código de hash do item de caixa com base no ID.
     * @return O código de hash do item de caixa.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Método para calcular o preço total do item.
     * @return O preço total do item.
     */
    public BigDecimal getTotalPrice() {
        if (quantity != null && price != null) {
            return price.multiply(BigDecimal.valueOf(quantity));
        } else {
            return BigDecimal.ZERO;
        }
    }
}
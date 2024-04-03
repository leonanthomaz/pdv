package app.vercel.leonanthomaz.pdv.model;

import app.vercel.leonanthomaz.pdv.enums.CashierStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Modelo que representa um caixa no sistema.
 */
@Entity
@Table(name = "tb_cashier")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código único associado ao caixa.
     */
    private String code;

    /**
     * Momento em que o caixa foi criado.
     */
    private Instant moment;

    /**
     * Status atual do caixa.
     */
    @Enumerated(EnumType.STRING)
    private CashierStatus status;

    /**
     * Total de vendas realizadas no caixa.
     */
    private BigDecimal total;

    /**
     * Conjunto de itens vendidos no caixa.
     */
    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CashierItem> items = new HashSet<>();

    /**
     * Adiciona um item ao caixa.
     * @param item O item a ser adicionado.
     */
    public void addItem(CashierItem item) {
        items.add(item);
        item.setCashier(this);
        calculateTotal();
    }

    /**
     * Calcula o total das vendas no caixa.
     */
    public void calculateTotal() {
        total = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Verifica se dois caixas são iguais com base no ID.
     * @param o O objeto a ser comparado.
     * @return true se os caixas forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashier cashier = (Cashier) o;
        return Objects.equals(id, cashier.id);
    }

    /**
     * Retorna o código de hash do caixa com base no ID.
     * @return O código de hash do caixa.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
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
    private String code;
    private Instant moment;
    @Enumerated(EnumType.STRING)
    private CashierStatus status;
    private BigDecimal total;

    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CashierItem> items = new HashSet<>();

    public void addItem(CashierItem item) {
        items.add(item);
        item.setCashier(this);
        calculateTotal();
    }

    public void calculateTotal() {
        total = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashier cashier = (Cashier) o;
        return Objects.equals(id, cashier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
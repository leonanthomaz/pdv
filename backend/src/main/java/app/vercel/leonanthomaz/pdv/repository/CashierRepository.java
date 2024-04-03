package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para operações relacionadas à entidade Cashier.
 */
public interface CashierRepository extends JpaRepository<Cashier, Long> {

    /**
     * Busca um caixa pelo código.
     *
     * @param code O código do caixa a ser encontrado.
     * @return O caixa encontrado, se existir.
     */
    Cashier findByCode(String code);
}

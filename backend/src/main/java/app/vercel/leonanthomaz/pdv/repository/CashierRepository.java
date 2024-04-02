package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierRepository extends JpaRepository<Cashier, String> {
    Cashier findByCode(String code);
}

package app.vercel.leonanthomaz.pdv.repository;

import app.vercel.leonanthomaz.pdv.enums.CashierStatus;
import app.vercel.leonanthomaz.pdv.model.Cashier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o repositorio cashierRepository.")
@ActiveProfiles("test")
class CashierRepositoryTest {

    @Autowired
    private CashierRepository cashierRepository;

    @Test
    @DisplayName("Retorna cashier por id")
    public void findCashierById_WhenSuccess_ReturnProduct() {
        Cashier cashier = Cashier.builder()
                .code("8A9BCAD33")
                .moment(Instant.now())
                .status(CashierStatus.PROCESSING)
                .build();
        Cashier cashierSaved = cashierRepository.save(cashier);

        Optional<Cashier> optionalCashier = cashierRepository.findById(cashier.getId());

        Assertions.assertThat(optionalCashier).isPresent();
        Assertions.assertThat(optionalCashier.get().getId()).isEqualTo(cashierSaved.getId());
        Assertions.assertThat(optionalCashier.get().getCode()).isEqualTo(cashierSaved.getCode());
        Assertions.assertThat(optionalCashier.get().getMoment()).isEqualTo(cashierSaved.getMoment());
        Assertions.assertThat(optionalCashier.get().getStatus()).isEqualTo(cashierSaved.getStatus());
    }
}
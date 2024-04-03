package app.vercel.leonanthomaz.pdv.service;

import app.vercel.leonanthomaz.pdv.enums.CashierStatus;
import app.vercel.leonanthomaz.pdv.model.Cashier;
import app.vercel.leonanthomaz.pdv.model.CashierItem;
import app.vercel.leonanthomaz.pdv.model.Product;
import app.vercel.leonanthomaz.pdv.repository.CashierRepository;
import app.vercel.leonanthomaz.pdv.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço responsável por lidar com operações relacionadas a caixas.
 */
@Service
@Log4j2
public class CashierService {

    @Autowired
    private CashierRepository cashierRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Gera um código aleatório para o caixa.
     *
     * @return O código aleatório gerado.
     */
    public static String generateRandomCode() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        return uuid.substring(0, Math.min(uuid.length(), 8)).toUpperCase();
    }

    /**
     * Cria um novo caixa.
     *
     * @return O caixa criado.
     */
    public Cashier createCashier() {
        Cashier cashier = new Cashier();
        String randomCode = generateRandomCode();
        cashier.setCode(randomCode);
        cashier.setStatus(CashierStatus.PROCESSING);
        cashier.setMoment(Instant.now());
        return cashierRepository.save(cashier);
    }

    /**
     * Encontra um caixa pelo código.
     *
     * @param code O código do caixa a ser encontrado.
     * @return O caixa encontrado.
     */
    public Cashier findByCode(String code) {
        return cashierRepository.findByCode(code);
    }

    /**
     * Adiciona um item ao caixa.
     *
     * @param code    O código do caixa.
     * @param codeBar O código de barras do produto a ser adicionado.
     * @return O caixa atualizado com o item adicionado.
     * @throws RuntimeException Se o caixa ou o produto não forem encontrados.
     */
    public Cashier addItemToCashier(String code, String codeBar) {
        Cashier cashier = findByCode(code);
        if (cashier == null) {
            throw new RuntimeException("Caixa não encontrado para o código: " + code);
        }
        Optional<Product> product = productRepository.findByCodeBar(codeBar);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            CashierItem existingItem = findItemByProductAndCashier(foundProduct, cashier);

            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + 1);
                existingItem.setTotalPrice(existingItem.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity())));
            } else {
                CashierItem newItem = CashierItem.builder()
                        .cashier(cashier)
                        .product(foundProduct)
                        .quantity(1)
                        .price(BigDecimal.valueOf(foundProduct.getPrice()))
                        .totalPrice(BigDecimal.valueOf(foundProduct.getPrice()))
                        .build();
                cashier.addItem(newItem);
            }
            cashier.calculateTotal();
            return cashierRepository.save(cashier);
        } else {
            throw new RuntimeException("Produto não encontrado para o código: " + codeBar);
        }
    }

    private CashierItem findItemByProductAndCashier(Product product, Cashier cashier) {
        return cashier.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna todos os itens do caixa.
     *
     * @return Lista contendo todos os itens do caixa.
     */
    public List<Cashier> findAll() {
        return cashierRepository.findAll();
    }

    /**
     * Atualiza o status de um caixa.
     *
     * @param code      O código do caixa.
     * @param newStatus O novo status a ser definido.
     */
    public void updateCashierStatus(String code, CashierStatus newStatus) {
        Cashier cashier = findByCode(code);
        cashier.setStatus(newStatus);
        cashierRepository.save(cashier);
    }

    /**
     * Retorna o valor total do caixa.
     *
     * @param code O código do caixa.
     * @return O valor total do caixa.
     */
    public BigDecimal getTotalAmount(String code) {
        Cashier cashier = findByCode(code);
        return cashier.getTotal();
    }
}
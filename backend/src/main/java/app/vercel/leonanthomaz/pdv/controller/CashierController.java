package app.vercel.leonanthomaz.pdv.controller;

import app.vercel.leonanthomaz.pdv.dto.ReceivedAmountDTO;
import app.vercel.leonanthomaz.pdv.dto.ResponseDTO;
import app.vercel.leonanthomaz.pdv.dto.UpdateStatusDTO;
import app.vercel.leonanthomaz.pdv.model.Cashier;
import app.vercel.leonanthomaz.pdv.service.CashierService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("cashier")
@Log4j2
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @PostMapping("create")
    public ResponseEntity<String> createCashier() {
        Cashier cashier = cashierService.createCashier();
        return ResponseEntity.status(HttpStatus.CREATED).body(cashier.getCode());
    }

    @GetMapping("{code}")
    public ResponseEntity<Cashier> findByCode(@PathVariable String code) {
        Cashier obj = cashierService.findByCode(code);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("add-item")
    public ResponseEntity<Cashier> addItemToCashier(@RequestBody ResponseDTO data) {
        Cashier updatedCashier = cashierService.addItemToCashier(data.getCode(), data.getCodeBar());
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCashier);
    }

    @GetMapping("all")
    public ResponseEntity<List<Cashier>> findAll() {
        List<Cashier> list = cashierService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update-status")
    public ResponseEntity<Void> updateCashierStatus(@RequestBody UpdateStatusDTO data) {
        log.info("ALTERAÇÃO DE STATUS - CÓDIGO: {}", data.getCodeCashier());
        log.info("ALTERAÇÃO DE STATUS - STATUS: {}", data.getStatus());
        cashierService.updateCashierStatus(data.getCodeCashier(), data.getStatus());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change")
    public ResponseEntity<BigDecimal> calculateChange(@RequestBody ReceivedAmountDTO data) {
        BigDecimal totalAmount = cashierService.getTotalAmount(data.getCodeCashier());
        log.info("TOTAL: {}", totalAmount);
        BigDecimal change = data.getReceivedAmount().subtract(totalAmount);
        log.info("TROCO: {}", change);
        return ResponseEntity.ok(change);
    }
}
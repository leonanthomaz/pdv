package app.vercel.leonanthomaz.pdv.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) para a quantidade recebida em um caixa.
 */
@Data
@Builder
public class ReceivedAmountDTO {

    /**
     * O código do caixa ao qual a quantidade foi recebida.
     */
    private String codeCashier;

    /**
     * A quantidade recebida no caixa.
     */
    private BigDecimal receivedAmount;
}

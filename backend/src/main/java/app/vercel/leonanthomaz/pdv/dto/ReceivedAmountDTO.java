package app.vercel.leonanthomaz.pdv.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ReceivedAmountDTO {
    private String codeCashier;
    private BigDecimal receivedAmount;
}

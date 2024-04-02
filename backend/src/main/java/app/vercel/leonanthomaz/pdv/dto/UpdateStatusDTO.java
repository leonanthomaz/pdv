package app.vercel.leonanthomaz.pdv.dto;

import app.vercel.leonanthomaz.pdv.enums.CashierStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateStatusDTO {
    private String codeCashier;
    private CashierStatus status;
}

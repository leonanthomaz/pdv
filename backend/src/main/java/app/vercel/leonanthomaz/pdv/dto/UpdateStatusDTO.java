package app.vercel.leonanthomaz.pdv.dto;

import app.vercel.leonanthomaz.pdv.enums.CashierStatus;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) para atualização do status de um caixa.
 */
@Data
@Builder
public class UpdateStatusDTO {

    /**
     * O código do caixa a ser atualizado.
     */
    private String codeCashier;

    /**
     * O novo status a ser atribuído ao caixa.
     */
    private CashierStatus status;
}

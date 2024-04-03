package app.vercel.leonanthomaz.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para adicionar um item ao caixa.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddItemToCashierDTO {

    /**
     * O código do caixa onde o item será adicionado.
     */
    private String code;

    /**
     * O código de barras do produto a ser adicionado ao caixa.
     */
    private String codeBar;
}

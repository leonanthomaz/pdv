package app.vercel.leonanthomaz.pdv.enums;

/**
 * Enumeração que representa os possíveis status de um caixa.
 */
public enum CashierStatus {

    /**
     * O caixa está em processo de atendimento.
     */
    PROCESSING("PROCESSING"),

    /**
     * O caixa está aguardando o pagamento.
     */
    WAITING_PAYMENT("WAITING_PAYMENT"),

    /**
     * O pagamento do caixa foi concluído.
     */
    PAID("PAID"),

    /**
     * O caixa foi finalizado.
     */
    FINISHED("FINISHED"),

    /**
     * O caixa foi cancelado.
     */
    CANCELED("CANCELED");

    private String code;

    /**
     * Construtor da enumeração CashierStatus.
     *
     * @param code O código do status.
     */
    private CashierStatus(String code) {
        this.code = code;
    }

    /**
     * Obtém o código do status.
     *
     * @return O código do status.
     */
    public String getCode() {
        return code;
    }
}

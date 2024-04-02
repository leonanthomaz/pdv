package app.vercel.leonanthomaz.pdv.enums;

public enum CashierStatus {
    PROCESSING("PROCESSING"),
    WAITING_PAYMENT("WAITING_PAYMENT"),
    PAID("PAID"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");

    private String code;

    private CashierStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

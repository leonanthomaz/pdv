package app.vercel.leonanthomaz.pdv.enums;

/**
 * Enumeração que representa o nível de autorização de usuário em um sistema.
 */
public enum UserRole {

    /**
     * Nível de administrador.
     */
    ADMIN("admin"),

    /**
     * Nível de usuário comum.
     */
    USER("user");

    private String role;

    /**
     * Construtor da enumeração UserRole.
     *
     * @param role O nível do usuário.
     */
    UserRole(String role) {
        this.role = role;
    }

    /**
     * Obtém o nível do usuário.
     *
     * @return O nível do usuário.
     */
    public String getRole() {
        return role;
    }
}

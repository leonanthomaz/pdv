package app.vercel.leonanthomaz.pdv.exception.user;

public class UserDetailsException extends RuntimeException {
    public UserDetailsException(String message) {
        super(message);
    }

    public UserDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
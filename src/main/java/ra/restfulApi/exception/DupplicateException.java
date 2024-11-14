package ra.restfulApi.exception;

public class DupplicateException extends RuntimeException{
    public DupplicateException(String message) {
        super(message);
    }
}

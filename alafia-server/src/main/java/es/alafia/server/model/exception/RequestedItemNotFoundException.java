package es.alafia.server.model.exception;

public class RequestedItemNotFoundException extends RuntimeException {

    public RequestedItemNotFoundException() {
        super();
    }

    public RequestedItemNotFoundException(String message) {
        super(message);
    }
}

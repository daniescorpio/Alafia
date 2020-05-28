package es.alafia.server.model.exception;

public class TableNotFoundException extends Exception {

    public TableNotFoundException() {
        super();
    }

    public TableNotFoundException(String message) {
        super(message);
    }
}

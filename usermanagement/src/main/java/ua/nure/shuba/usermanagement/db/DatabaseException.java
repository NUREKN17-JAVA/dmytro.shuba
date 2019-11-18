package ua.nure.shuba.usermanagement.db;

public class DatabaseException extends Exception {

    public DatabaseException(Exception e) {
        super(e);
    }

    public DatabaseException(String s) {
        super(s);
    }
}

package adam.exceptions;

public class CubeCreationExceptions extends Exception {
    public CubeCreationExceptions(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CubeCreationExceptions(String message) {
        super(message);
    }
}

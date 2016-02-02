package featureStudy;

public class ExceptionGenerator {

    public boolean catchExceptionAndReturnFalse() {
        try {
            throw new RuntimeException("it is a RuntimeException");
        } catch (Exception e) {
            return false;
        }
    }

    public void throwRuntimeException() throws Exception {
        throw new RuntimeException("A runtime exception occurs");
    }
}

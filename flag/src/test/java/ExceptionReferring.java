import featureStudy.ExceptionGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ExceptionReferring {

    ExceptionGenerator generator;
    @Before
    public void setUp() {
        generator = new ExceptionGenerator();
    }

    @Test
    public void ifReturnWhenCaughtException() throws Exception {
        assertFalse(generator.catchExceptionAndReturnFalse());
    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeException() throws Exception {
        generator.throwRuntimeException();
    }
}

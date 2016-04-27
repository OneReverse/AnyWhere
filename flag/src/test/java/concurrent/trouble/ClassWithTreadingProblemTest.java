package concurrent.trouble;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.fail;

public class ClassWithTreadingProblemTest {
    @Test
    public void twoThreadsShouldFailEventually() throws Exception {
        final ClassWithTreadingProblem classWithTreadingProblem = new ClassWithTreadingProblem();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                classWithTreadingProblem.takeNextId();
            }
        };

        for (int i = 0; i < 5000; ++i) {
            int startingId = classWithTreadingProblem.nextId;
            int expectedId= 2 + startingId;
            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            int endingId = classWithTreadingProblem.nextId;

            if (endingId != expectedId)
                return;
        }

        fail("Should have exposed a threading issue but it did not.");
    }

    @Test
    public void anOtherWayToValidate() throws Exception {
        final ClassWithTreadingProblem classWithTreadingProblem = new ClassWithTreadingProblem();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                classWithTreadingProblem.takeNextId();
            }
        };
        Executor executor = Executors.newFixedThreadPool(2);

        int startingId = classWithTreadingProblem.nextId;
        int expectedId= 2 + startingId;
        executor.execute(runnable);
        int endingId = classWithTreadingProblem.nextId;

        if (endingId != expectedId)
            return;

    fail("Should have exposed a threading issue but it did not.");
    }

}

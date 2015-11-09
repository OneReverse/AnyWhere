import concurrent.IdGenerator;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by OneReverse on 2015/10/13.
 */
public class AomicInteger {
    @Test
    public void execute() throws InterruptedException {
        IdGenerator generator = new IdGenerator();
        Thread t1 = new Thread(generator);
        Thread t2 = new Thread(generator);
        Thread t3 = new Thread(generator);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

}

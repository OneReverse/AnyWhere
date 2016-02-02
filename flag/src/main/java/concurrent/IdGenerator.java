package concurrent;
/**
 * Created by OneReverse on 2015/10/13.
 */
public class IdGenerator implements Runnable{
    private int lastIdUsed;

    public synchronized void run() {
        System.out.println(++lastIdUsed);
    }
}

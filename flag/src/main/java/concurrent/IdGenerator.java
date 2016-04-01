package concurrent;

public class IdGenerator implements Runnable{
    private int lastIdUsed;

    public synchronized void run() {
        System.out.println(++lastIdUsed);
    }
}

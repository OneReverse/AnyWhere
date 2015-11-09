package netServer;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by OneReverse on 2015/10/12.
 */
public class ThreadedEchoServer {
    public static void main(String[] args ) {
        try {
        int i = 1;
        ServerSocket s = new ServerSocket(8189);
        Executor executor = Executors.newFixedThreadPool(3);
        while (true)
        {
            Socket incoming = s.accept();
            System.out.println("Spawning " + i);
            Runnable r = new ThreadedEchoHandler(incoming);
            executor.execute(r);
//            Thread t = new Thread(r);
//            t.start();
            i++;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}

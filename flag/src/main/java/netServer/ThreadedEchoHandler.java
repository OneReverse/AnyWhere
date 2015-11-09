package netServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by OneReverse on 2015/10/12.
 */
public class ThreadedEchoHandler implements Runnable  {
    private Socket incoming;
    /** Constructs a handler.
     *  @param i the incoming socket  */
    public ThreadedEchoHandler(Socket i) {
        incoming = i;
    }
    public void run()
    {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
                out.println( "Hello! Enter BYE to exit." );
                // echo client input
                boolean done = false;
                while (!done && in.hasNextLine())
                {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equals("BYE"))
                        done = true;
                }
            }
            finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by OneReverse on 2015/10/25.
 */
public class StreamAndText {
    @Test
    public void test() throws IOException {
        String string = "My song for you this evening is not to make you sad.";
        int index = 0;
        while(index < string.length()) {
            System.out.printf(string.charAt(index++) + " ");
        }
        String seprator = File.separator + "n";
        System.out.println("\n" + System.getProperty("user.dir"));
    }

    @Test
    public void readFromConsole() {
        String line;
        Scanner scanner = new Scanner(System.in);
//        while ((line = scanner.nextLine()) != null) {
//            System.out.println(line);
//        }
        line = scanner.nextLine();
        System.out.println(line);
    }

    @Test
    public void aliasesOfCharset() {
        Charset cset = Charset.forName("ISO-8859-1");
        Set<String> aliases = cset.aliases();
        for (String alias : aliases)
            System.out.println(alias);
    }
}

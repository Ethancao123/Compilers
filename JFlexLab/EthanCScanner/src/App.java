import scanner.EthanCScanner;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("JFlexLab/test.txt")));
        EthanCScanner scan = new EthanCScanner(br);
        boolean eof = false;
        String token = "";
        do
        {
            token = scan.nextToken();
            System.out.println(token);
        } while(!token.equals("END"));
    }
}

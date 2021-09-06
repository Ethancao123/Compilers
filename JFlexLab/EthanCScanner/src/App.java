import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("JFlexLab/test.txt")));
        EthanCScanner scan = new EthanCScanner(br);
        String token = "";
        do
        {
            token = scan.nextToken();
            System.out.println(token);
        } while(!token.equals("END"));
    }
}

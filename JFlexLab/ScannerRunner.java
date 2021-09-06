import scanner.EthanCScanner;
import java.io.*;

public class ScannerRunner {
    public static void main(String[] args)
    {
        File file = new File("C:\\Users\\ethan\\OneDrive\\Documents\\GitHub\\Compilers\\JFlex Lab\\test.txt");
        System.out.println(file.exists());
        BufferedReader br = new BufferedReader(new FileReader(file));
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

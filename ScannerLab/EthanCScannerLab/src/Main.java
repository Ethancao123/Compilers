import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        EthanCScannerLab in = new EthanCScannerLab(new FileInputStream("ScannerLab/test.txt"));
        while(in.hasNext())
        {
            String token = in.nextToken();
            if(!token.equals(""))
                System.out.println(token);
        }
    }
}

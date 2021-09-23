import java.io.*;

/**
 * Runs a scanner for a Pascal compiler
 * 
 * @author Ethan Cao
 * @version 9/7/21
 */
public class Main 
{
    /**
     * Runs a scanner for a Pascal compiler
     * 
     * @param args String arguments
     * @throws FileNotFoundException When test.txt does not exist
     * @throws ScanErrorExcerption When a parse error occurs
     */
    public static void main(String[] args) throws FileNotFoundException, ScanErrorException
    {
        EthanCScannerLab in = new EthanCScannerLab(new FileInputStream("Scanner/test.txt"));
        while(in.hasNext())
        {
            String token = in.nextToken();
            if(!token.equals(""))
                System.out.println(token);
        }
    }
}

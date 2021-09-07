import java.io.*;

/**
 * Runner for the JFlex generated scanner
 * 
 * @author Ethan Cao
 * @author John Zeng
 * @author Allen Boyce
 * @version 9/6/2021
 */
public class App 
{
    /**
     * Creates a stream of tokens from "test.txt" and prints them to the terminal
     * @param args String arguments
     * @throws FileNotFoundException when "test.txt" is not found
     * @throws IOException if a read error occurs
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(new File("JFlexLab/test.txt")));
        JFlexScanner scan = new JFlexScanner(br);
        String token = "";
        do
        {
            token = scan.nextToken();
            System.out.println(token);
        } while(!token.equals("END"));
    }
}

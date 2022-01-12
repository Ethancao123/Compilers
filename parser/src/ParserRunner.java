import java.io.*;
/**
 * Runner for the parser classs
 * @author Ethan Cao
 * @version 1/12/22
 */
public class ParserRunner 
{
    /**
     * Parses and executes a test file
     * @param args String arguments
     * @throws Exception If an illegal character is scanned
     */
    public static void main(String[] args) throws Exception 
    {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseProgram().exec();
    }
}


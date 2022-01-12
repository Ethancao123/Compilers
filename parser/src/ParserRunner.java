import java.io.*;
/**
 * Runner for the parser class
 * @author Ethan Cao
 * @version 1/12/22
 */
public class ParserRunner 
{
    /**
     * Parses and runs pascal code in a test file
     * @param args String arguments
     * @throws Exception if an illegal character is scanned
     */
    public static void main(String[] args) throws Exception 
    {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseProgram().exec();
    }
}


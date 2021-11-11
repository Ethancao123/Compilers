import java.io.*;
/**
 * no
 * @author nobody
 * @version never
 */
public class ParserRunner 
{
    /**
     * no  
     * @param args no  
     * @throws Exception no  
     */
    public static void main(String[] args) throws Exception 
    {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseProgram().exec();
    }
}


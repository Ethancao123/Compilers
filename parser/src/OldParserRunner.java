import java.io.*;
/**
 * no
 * @author nobody
 * @version never
 */
public class OldParserRunner 
{
    /**
     * no  
     * @param args no  
     * @throws Exception no  
     */
    public static void main(String[] args) throws Exception 
    {
        OldParser parse = new OldParser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseStatement();
    }
}


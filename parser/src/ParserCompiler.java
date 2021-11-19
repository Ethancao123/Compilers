import ast.Emitter;
import java.io.*;

public class ParserCompiler 
{
    /**
     * no  
     * @param args no  
     * @throws Exception no  
     */
    public static void main(String[] args) throws Exception 
    {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseProgram().compile(new Emitter("out.txt"));
    }
}

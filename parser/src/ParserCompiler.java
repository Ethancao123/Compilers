import ast.Emitter;
import java.io.*;

/**
 * Runs a compiler for pascal
 * @author Ethan Cao
 * @version 1/12/22
 */
public class ParserCompiler 
{
    /**
     * Compiles a Pascal program into assembly
     * @param args String arguments
     * @throws Exception if an illegal argument is scanned
     */
    public static void main(String[] args) throws Exception 
    {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseProgram().compile(new Emitter("out.asm"));
    }
}

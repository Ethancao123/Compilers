package ast;
import java.util.*;

/**
 * Block class for a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Block extends Statement
{
    private List<Statement> stmts;

    /**
     * Constructor for objects of the Block class
     * @param s The list of statements to be processed
     */
    public Block(List<Statement> s)
    {
        stmts = s;
    }

    /**
     * Executes the block of statements
     * @param env the environment to execute the statements in 
     */
    public void exec(Environment env)
    {
        for(int i = 0; i < stmts.size(); i++)
            stmts.get(i).exec(env);
    }

    public void compile(Emitter e)
    {
        e.emit("# block of statements");
        for(int i = 0; i < stmts.size(); i++)
            stmts.get(i).compile(e);
    }
}

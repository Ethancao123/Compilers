package ast;

/**
 * If Class for a Pascal Compiler
 * @author Ethan Cao
 * @version 10/25/21
 */
public class If extends Statement
{
    private Statement stmt;
    private Condition cond;
    
    /**
     * Constructor for objects of the If class
     * @param s the statement to execute
     * @param c the condition that must be fufilled
     */
    public If(Statement s, Condition c)
    {
        stmt = s;
        cond = c;
    }

    /**
     * Executes the if statement
     * @param env the environment to execute the statement in
     */
    public void exec(Environment env)
    {
        if(cond.eval(env))
        {
            stmt.exec(env);
        }
    }

    /**
     * Compiles an if statement
     * @param e the emitter to write the file
     */
    public void compile(Emitter e)
    {
        e.emit("# if statement");
        String tag = "endif" + e.nextLabelID();
        cond.compile(e, tag);
        stmt.compile(e);
        e.emit(tag + ":");
    }
}

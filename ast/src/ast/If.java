package ast;

/**
 * If Class for a Pascal Compiler
 * @author Ethan Cao
 * @version 10/25/21
 */
public class If extends Statement
{
    private Statement stmt;
    private Expression cond;
    private Statement elseStmt;
    
    /**
     * Constructor for objects of the If class
     * @param s the statement to execute
     * @param c the condition that must be fufilled
     * @param es the statement to execute if condition is false
     */
    public If(Statement s, Expression c, Statement es)
    {
        stmt = s;
        cond = c;
        elseStmt = es;
    }

    /**
     * Executes the if statement
     * @param env the environment to execute the statement in
     */
    public void exec(Environment env)
    {
        if(cond.eval(env) != 0)
        {
            stmt.exec(env);
        }
        else if(elseStmt != null)
        {
            elseStmt.exec(env);
        }
    }
}

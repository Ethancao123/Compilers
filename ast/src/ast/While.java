package ast;

/**
 * While class for a Pascal compiler
 * @author Ethan Cao
 * @version 10/25/21
 */
public class While extends Statement
{
    Statement stmt;
    Condition cond;

    /**
     * Constructor for objects of the While class
     * @param s the statement to execute in the loop
     * @param c the condition to execute the loop under
     */
    public While(Statement s, Condition c)
    {
        stmt = s;
        cond = c;
    }

    /**
     * Executes the statments while the condition is true
     * @param env the environment to execute the statements in
     */
    public void exec(Environment env)
    {
        while(cond.eval(env))
        {
            stmt.exec(env);
        }
    }

    public void compile(Emitter e)
    {
        e.emit("# while loop");
        String endTag = "whileEnd" + e.nextLabelID();
        String beginTag = "whileBegin" + e.nextLabelID();
        cond.compile(e, endTag);
        e.emit(beginTag + ":");
        stmt.compile(e);
        cond.compileInverse(e, beginTag);
        e.emit(endTag + ":");
    }
}

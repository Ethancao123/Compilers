package ast;

/**
 * Assignment Statement for a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Assignment extends Statement
{
    private Variable var;
    private Expression exp;

    /**
     * Constructor for objects of the Assignement class
     * @param v The name of the variable to assign
     * @param e The expression to assign that variable to
     */
    public Assignment(String v, Expression e)
    {
        var = new Variable(v);
        exp = e;
    }

    /**
     * Executes the variable assignment
     * @param env the environment to execute the assignment in
     */
    public void exec(Environment env)
    {
        env.setVariable(var.getName(), exp.eval(env));
    }
}

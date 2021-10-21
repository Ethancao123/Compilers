package ast;

/**
 * Class for Writeln statements in a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for objects of the Writeln class
     * @param exp the expression to write to the terminal
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * writes the expression to the terminal
     * @param env the environment to evaluate the exprssion in
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}

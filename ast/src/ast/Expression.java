package ast;

/**
 * Abstract class for expressions in a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public abstract class Expression 
{
    /**
     * Evaluates the expression
     * @param env the environment to evaluate the expression in
     * @return the value of the expression
     */
    public abstract int eval(Environment env);

    /**
     * Complies the statemet
     * @param e Emitter which writes the MIPS code to a file
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Code not implemented");
    }
}
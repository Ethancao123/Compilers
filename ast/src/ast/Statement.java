package ast;

/**
 * Abstract Class for statement operators in a Pascal complier
 * @author Ethan Cao
 * @version 10/21/21
 */
public abstract class Statement
{
    /**
     * Executes a Statement
     * @param env the environment to execute the statement in
     */
    public abstract void exec(Environment env);
}

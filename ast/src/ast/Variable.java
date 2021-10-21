package ast;

/**
 * Class for variables in a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Variable extends Expression
{

    private String name;

    /**
     * Constructor for objects of the Variable class
     * @param n the name of the variable
     */
    public Variable(String n)
    {
        name = n;
    }

    /**
     * Getter for the name of the variable
     * @return the name of the variable
     */
    public String getName()
    {
        return name;
    }

    /**
     * Evaluates the value of a variable
     * @param env the environment to evaluate the variable in
     * @return the value of the variable
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}

package ast;

/**
 * Class for number objects in a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor for objects of the number class
     * @param val the value of the number
     */
    public Number(int val)
    {
        value = val;
    }

    /**
     * Evaluates the value of the number
     * @param env the environment to evaluate the number in
     * @return the value of the number
     */
    public int eval(Environment env)
    {
        return value;
    }

    public void compile(Emitter e)
    {
        e.emit("# loading number");
        e.emit("li $v0 " + value);
    }
}

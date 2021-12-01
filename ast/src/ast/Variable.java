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
        if(name.indexOf(':') == -1)
            return name;
        return name.substring(name.indexOf(':') + 2);
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

    public void compile(Emitter e)
    {
        e.emit("la $t0 VAR" + this.getName());
        e.emit("lw $v0 0($t0)");
    }
}

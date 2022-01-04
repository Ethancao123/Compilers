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
        if(n.indexOf(':') == -1)
            name = n;
        name = n.substring(n.indexOf(':') + 2);
    }
    /**
     * Getter for the name of the variable
     * @return the name of the variable
     */
    public String getName()
    {
        return name;
        // if(name.indexOf(':') == -1)
        //     return name;
        // return name.substring(name.indexOf(':') + 1);
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

    /**
     * Compiles a variable
     * @param e the emitter to write the file
     */
    public void compile(Emitter e)
    {
        e.emit("# evaluates a variable");
        if(e.isLocalVariable(this.getName()))
        {
            int offset = e.getOffset(this.getName());
            e.emit("lw $v0 " + offset + "($sp)");
        }
        else
        {
            e.emit("la $t0 VAR" + this.getName());
            e.emit("lw $v0 0($t0)");
        }
        
    }
}

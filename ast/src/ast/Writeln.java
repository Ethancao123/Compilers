package ast;

/**
 * Class for Writeln statements in a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Writeln extends Statement
{
    private Expression exp;
    private Variable input;

    /**
     * Constructor for objects of the Writeln class
     * @param exp the expression to write to the terminal
     * @param var the value of the user input
     */
    public Writeln(Expression exp, Variable var)
    {
        this.exp = exp;
        input = var;
    }

    /**
     * writes the expression to the terminal
     * @param env the environment to evaluate the exprssion in
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
        //TODO: ask user for input and assign input
    }

    /**
     * Compiles a print statement
     * @param e the emitter to write the file
     */
    public void compile(Emitter e)
    {
        e.emit("# print statement");
        exp.compile(e);
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");
        //make new line
        e.emit("li $v0 4");
        e.emit("la $a0 newLine");
        e.emit("syscall");
    }
}

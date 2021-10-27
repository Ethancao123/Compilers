package ast;

/**
 * Program class for a pascal compiler
 * @author Ethan Cao
 * @version 10/27/21
 */
public class Program
{
    Environment env; //variables and procedures
    Statement stmts; //everything except for variables and procedures

    /**
     * Constructor for objects of the Program class
     * @param e Environment of the program
     * @param s Statements of the program
     */
    public Program(Environment e, Statement s)
    {
        env = e;
        stmts = s;
        env.printProcedures();
    }

    /**
     * Executes the program
     */
    public void exec()
    {
        stmts.exec(env);
        env.printVariables();
    }
}

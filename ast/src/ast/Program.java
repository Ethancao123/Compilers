package ast;

import java.util.*;

/**
 * Program class for a pascal compiler
 * @author Ethan Cao
 * @version 10/27/21
 */
public class Program
{
    List<Variable> vars;
    Environment env; //variables and procedures
    Statement stmts; //everything except for variables and procedures

    /**
     * Constructor for objects of the Program class
     * @param e Environment of the program
     * @param s Statements of the program
     * @param v the variables declared for the program
     */
    public Program(Environment e, Statement s, List<Variable> v)
    {
        env = e;
        stmts = s;
        vars = v;
        //env.printProcedures();
    }

    /**
     * Executes the program
     */
    public void exec()
    {
        if(stmts == null)
            return;
        stmts.exec(env);
        //env.printVariables();
    }
}

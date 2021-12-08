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

    /**
     * Compiles a pascal program
     * @param e the emitter to write the file
     */
    public void compile(Emitter e)
    {
        e.emit("#");
        e.emit("#   Auto generated code created by a PASCAL compiler");
        e.emit("#   @author Ethan Cao");
        e.emit("#   @version 12/2/21");
        e.emit("#");
        e.emit(".data");
        e.emit("newLine: .asciiz \"\\n\"");
        for(Variable v : vars)
        {
            e.emit("VAR" + v.getName() + ": .word 0");
        }
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main:");
        stmts.compile(e);
        e.emit("# ending program");
        e.emit("li $v0 10");
        e.emit("syscall");
        env.compile(e);
    }
}

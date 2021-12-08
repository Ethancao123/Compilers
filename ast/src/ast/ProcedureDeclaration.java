package ast;
import java.util.*;

/**
 * Class for procedure declarations in a Pascal compiler
 * @author Ethan Cao
 * @version 10/27/21
 */
public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmts;
    private List<String> a;

    /**
     * Constructor for objects of the ProcedureDeclaration class
     * @param n the name of the procedure
     * @param s the statements within the procedure
     */
    public ProcedureDeclaration(String n, Statement s)
    {
        name = n;
        stmts = s;
    }

    /**
     * Constructor for objects of the ProcedureDeclaration class
     * @param n the name of the procedure
     * @param s the statements within the procedure
     * @param a arguments of the procedure
     */
    public ProcedureDeclaration(String n, Statement s, List<String> a)
    {
        name = n;
        stmts = s;
        this.a = a;
    }

    /**
     * Declares the procedure in the environment
     * @param env the environemnt to execute the declaration in
     */
    public void exec(Environment env)
    {
        env.setProcedure(name,this);
    }

    /**
     * Gets the list of arguments required for a procedure
     * @return the list of arguments
     */
    public List<String> getArgs()
    {
        return a;
    }

    /**
     * Gets the statements of a procedure
     * @return the statements of the procedure
     */
    public Statement getStmts()
    {
        return stmts;
    }

    public void compile(Emitter e)
    {
        e.setProcedureContext(this);
        e.emit("PROC" + name.substring(name.indexOf(":") + 2) + ":");
        stmts.compile(e);
        e.emit("jr $ra");
        e.clearProcedureContext();
    }
}

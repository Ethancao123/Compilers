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
    private List<String> localVar;

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
    public ProcedureDeclaration(String n, Statement s, List<String> a, List<String> l)
    {
        name = n;
        stmts = s;
        for(int i = 0; i < a.size(); i++)
        {
            a.set(i, a.get(i).substring(a.get(i).indexOf(':')+2));
        }
        this.a = a;
        localVar = l;
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

    public String getName()
    {
        return name.substring(name.indexOf(":") + 2);
    }

    public List<String> getLocalVars()
    {
        return localVar;
    }

    public void compile(Emitter e)
    {
        e.emit("PROC" + name.substring(name.indexOf(":") + 2) + ":");
        e.emit("li $t2 0");
        e.emitPush("$t2");
        for(int i = 0; i < localVar.size(); i++)
        {
            e.emitPush("$t2");
        }
        e.setProcedureContext(this);
        stmts.compile(e);
        e.clearProcedureContext();
        for(int i = 0; i < localVar.size(); i++)
        {
            e.emitPop("$t2");
        }
        e.emit("jr $ra");
    }
}

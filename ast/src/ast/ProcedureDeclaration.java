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
    private List<String> args;

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
        args = a;
    }

    /**
     * Executes the procedure declaration
     * @param env the environemnt to execute the declaration in
     */
    public void exec(Environment env)
    {
        env.setProcedure(name,this);
    }

    public void run(Environment env, List<Integer> args)
    {
        if(args != null)
        {
            if(args.size() != this.args.size())
                throw new IllegalArgumentException("Parameters do not match declared params");
            Environment subEnv = new Environment(env);
            for(int i = 0; i < args.size(); i++)
            {
                subEnv.setVariable(this.args.get(i), args.get(i));
            }
            stmts.exec(subEnv);
        }
        else
        {
            stmts.exec(env);
        }
    }
}

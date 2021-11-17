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
     * Executes the procedure declaration
     * @param env the environemnt to execute the declaration in
     */
    public void exec(Environment env)
    {
        env.setProcedure(name,this);
    }

    public int run(Environment env, List<Expression> args)
    {
        Environment subEnv = null;
        if(args != null)
        {
            if(args.size() != this.a.size())
                throw new IllegalArgumentException("Parameters do not match declared params");
            subEnv = new Environment(env);
            for(int i = 0; i < args.size(); i++)
            {
                subEnv.setVariable(this.a.get(i), args.get(i).eval(subEnv));
            }
            stmts.exec(subEnv);
        }
        else
        {
            stmts.exec(new Environment(env));
        }
        if(subEnv != null && subEnv.hasVariable(name))
            return subEnv.getVariable(name);
        return 0;
    }
}

package ast;

import java.util.*;

/**
 * Class for procedure calls in a Pascal compiler
 * @author Ethan Cao
 * @version 10/27/21
 */
public class ProcedureCall extends Expression
{
    private String name;
    private List<Expression> args;
    /**
     * Constructor for objects of the ProcedureCall class
     * @param n the name of the procedure that is called
     */
    public ProcedureCall(String n)
    {
        name = n;
    }

    /**
     * Constructor for objects of the ProcedureCall class
     * @param n the name of the procedure that is called
     * @param a the arguments of the procedure call
     */
    public ProcedureCall(String n, List<Expression> a)
    {
        name = n;
        args = a;
    }

    /**
     * Evaluates the procedure call
     * @param env the environment to evaluate the procedure call in
     * @return the result of the procedure call
     */
    public int eval(Environment env)
    {
        List<String> argsList = env.getProcedure(name).getArgs();
        Statement stmts = env.getProcedure(name).getStmts();
        Environment subEnv = new Environment(env);
        if(args != null)
        {
            if(args.size() != argsList.size())
                throw new IllegalArgumentException("Parameters do not match declared params");
            for(int i = 0; i < args.size(); i++)
            {
                subEnv.declareVariable(argsList.get(i), args.get(i).eval(env));
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

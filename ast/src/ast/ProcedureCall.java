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
        List<String> localvars = env.getProcedure(name).getLocalVars();
        Environment subEnv = new Environment(env);
        if(args != null)
        {
            if(args.size() != argsList.size())
                throw new IllegalArgumentException("Parameters do not match declared params");
            for(int i = 0; i < args.size(); i++)
            {
                subEnv.declareVariable(argsList.get(i), args.get(i).eval(env));
            }
        }
        if(localvars != null)
        {
            for(int i = 0; i < localvars.size(); i++)
            {
                subEnv.declareVariable(localvars.get(i), 0);
            }
        }
        stmts.exec(subEnv);
        if(subEnv != null && subEnv.hasVariable(name))
            return subEnv.getVariable(name);
        return 0;
    }

    public void compile(Emitter e)
    {
        e.emitPush("$ra");
        for(Expression exp : args)
        {
            exp.compile(e);
            e.emitPush("$v0");
        }
        e.emit("jal PROC" + name.substring(name.indexOf(":") + 2));
        for(Expression exp : args)
        {
            e.emitPop("$t1");
        }
        e.emitPop("$ra");
        e.emitPop("$v0");
    }
}

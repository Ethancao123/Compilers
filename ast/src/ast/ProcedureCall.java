package ast;

public class ProcedureCall extends Expression
{
    public String name;

    public ProcedureCall(String n)
    {
        name = n;
    }

    public int eval(Environment env)
    {
        env.getProcedure(name).exec(env);
        return 0;
    }
}

package ast;

public class ProcedureCall extends Statement
{
    public String name;

    public ProcedureCall(String n)
    {
        name = n;
    }

    public void exec(Environment env)
    {
        env.getProcedure(name).exec(env);
    }
}

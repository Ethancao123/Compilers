package ast;

public class If extends Statement{
    private Statement stmt;
    private Condition cond;
    
    public If(Statement s, Condition c)
    {
        stmt = s;
        cond = c;
    }

    public void exec(Environment env)
    {
        if(cond.eval(env))
        {
            stmt.exec(env);
        }
    }
}

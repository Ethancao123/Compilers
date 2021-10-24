package ast;

public class While extends Statement
{
    Statement stmt;
    Condition cond;

    public While(Statement s, Condition c)
    {
        stmt = s;
        cond = c;
    }

    public void exec(Environment env)
    {
        while(cond.eval(env))
        {
            stmt.exec(env);
        }
    }
}

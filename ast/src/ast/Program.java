package ast;

public class Program
{
    Environment env; //variables and procedures
    Statement stmts; //everything except for variables and procedures

    public Program(Environment e, Statement s)
    {
        env = e;
        stmts = s;
    }

    public void exec()
    {
        stmts.exec(env);
    }
}

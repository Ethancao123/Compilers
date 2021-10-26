package ast;

public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmts;

    public ProcedureDeclaration(String n, Statement s)
    {
        name = n;
        stmts = s;
    }

    public void exec(Environment env)
    {
        env.setProcedure(name,stmts);
    }
}

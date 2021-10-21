package ast;

public class If extends Statement{
    private Statement stmt;
    private Condition cond;
    
    public If(Statement s, Condition c)
    {
        stmt = s;
        cond = c;
    }
}

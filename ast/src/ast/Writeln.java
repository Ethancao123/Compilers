package ast;

public class Writeln extends Statement
{
    private Expression exp;

    public Writeln(Expression exp)
    {
        super();
        this.exp = exp;
    }
}

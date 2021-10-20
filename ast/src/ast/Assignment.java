package ast;

public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    public Assignment(String v, Expression e)
    {
        var = v;
        exp = e;
    }
}

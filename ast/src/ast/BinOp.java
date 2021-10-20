package ast;

public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    public BinOp(String o, Expression e1, Expression e2)
    {
        op = o;
        exp1 = e1;
        exp2 = e2;
    }
}

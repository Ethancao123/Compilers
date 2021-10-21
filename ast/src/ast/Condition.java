package ast;

public class Condition {
    String relop;
    Expression exp1;
    Expression exp2;

    public Condition(Expression e1, String r, Expression e2)
    {
        relop = r;
        exp1 = e1;
        exp2 = e2;
    }
}

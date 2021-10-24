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

    public boolean eval(Environment env)
    {
        int result1 = exp1.eval(env);
        int result2 = exp2.eval(env);
        switch(relop)
        {
            case "=":
                return result1 == result2;
            case "<>":
                return result1 != result2;
            case "<":
                return result1 < result2;
            case ">":
                return result1 > result2;
            case "<=":
                return result1 <= result2;
            case ">=":
                return result1 >= result2;
            default:
                throw new IllegalArgumentException(relop + " is not an relative operator");
        }
    }
}

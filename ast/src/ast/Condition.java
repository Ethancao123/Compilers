package ast;

/**
 * Condition Class for a Pascal compiler
 * @author Ethan Cao
 * @version 10/25/21 
 */
public class Condition 
{
    String relop;
    Expression exp1;
    Expression exp2;

    /**
     * Constructor for objects of the Condition class
     * @param e1 the first expression in the conditional statement
     * @param r the relative operator of the conditional statement
     * @param e2 the second expression in the conditional statement
     */
    public Condition(Expression e1, String r, Expression e2)
    {
        relop = r;
        exp1 = e1;
        exp2 = e2;
    }

    /**
     * Evaluates the result of the conditional statement
     * @param env the environment to evaluate the result in
     * @return the result of the conditional statement
     */
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

package ast;

/**
 * Binary Operator class for a Pascal Compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for Objects of the BinOp class
     * @param o the operator in the expression
     * @param e1 the first expression in the equation
     * @param e2 the second expression in the equation
     */
    public BinOp(String o, Expression e1, Expression e2)
    {
        op = o;
        exp1 = e1;
        exp2 = e2;
    }

    /**
     * Evaluates an operation of two expression
     * @param env the environment to evaluate the operation in
     * @return the result of the calcuation
     */
    public int eval(Environment env)
    {
        //System.out.println(exp1);
        //System.out.println(exp2);
        //System.out.println(op);
        int result1 = exp1.eval(env);
        int result2 = exp2.eval(env);
        switch(op)
        {
            case "*":
                return result1 * result2;
            case "/":
                return result1 / result2;
            case "+":
                return result1 + result2;
            case "-":
                return result1 - result2;
            case "%":
                return result1 % result2;
            default:
                throw new IllegalArgumentException(op + " is not an operator");
        }
    }
}

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
            case "SEP : =":
                return result1 == result2;
            case "SEP : <>":
                return result1 != result2;
            case "SEP : <":
                return result1 < result2;
            case "SEP : >":
                return result1 > result2;
            case "SEP : <=":
                return result1 <= result2;
            case "SEP : >=":
                return result1 >= result2;
            default:
                throw new IllegalArgumentException(relop + " is not an relative operator");
        }
    }

    /**
     * Compiles the conditional statement
     * @param e the emitter to write the file
     * @param tag the tag jump to
     */
    public void compile(Emitter e, String tag)
    {
        exp2.compile(e);
        e.emit("move $t1 $v0");
        exp1.compile(e);
        switch(relop)
        {
            case "SEP : =":
                e.emit("bne $v0 $t1 " + tag);
                return;
            case "SEP : <>":
                e.emit("beq $v0 $t1 " + tag);
                return;
            case "SEP : <":
                e.emit("bge $v0 $t1 " + tag);
                return;
            case "SEP : >":
                e.emit("ble $v0 $t1 " + tag);
                return;
            case "SEP : <=":
                e.emit("bgt $v0 $t1 " + tag);
                return;
            case "SEP : >=":
                e.emit("blt $v0 $t1 " + tag);
                return;
            default:
                throw new IllegalArgumentException(relop + " is not an relative operator");
        }
    }

    /**
     * Compiles the invese of the conditional statement/
     * @param e the emitter to write the file
     * @param tag the tag to jump to
     */
    public void compileInverse(Emitter e, String tag)
    {
        exp2.compile(e);
        e.emit("move $t1 $v0");
        exp1.compile(e);
        switch(relop)
        {
            case "SEP : =":
                e.emit("beq $v0 $t1 " + tag);
                return;
            case "SEP : <>":
                e.emit("bne $v0 $t1 " + tag);
                return;
            case "SEP : <":
                e.emit("blt $v0 $t1 " + tag);
                return;
            case "SEP : >":
                e.emit("bgt $v0 $t1 " + tag);
                return;
            case "SEP : <=":
                e.emit("ble $v0 $t1 " + tag);
                return;
            case "SEP : >=":
                e.emit("bge $v0 $t1 " + tag);
                return;
            default:
                throw new IllegalArgumentException(relop + " is not an relative operator");
        }
    }
}

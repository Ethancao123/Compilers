package ast;

import java.util.Scanner;

/**
 * Class for Writeln statements in a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Writeln extends Statement
{
    private Expression exp;
    private String input;

    /**
     * Constructor for objects of the Writeln class
     * @param exp the expression to write to the terminal
     * @param var the value of the user input
     */
    public Writeln(Expression exp, String var)
    {
        this.exp = exp;
        input = var;
    }

    /**
     * writes the expression to the terminal
     * @param env the environment to evaluate the exprssion in
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
        if(input != null)
        {
            Scanner sc = new Scanner(System.in);
            int in = sc.nextInt();
            sc.close();
            Assignment a = new Assignment(input, new Number(in));
            a.exec(env);
        }
    }
}

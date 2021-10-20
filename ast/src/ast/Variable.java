package ast;

public class Variable extends Expression
{

    private String name;

    public Variable(String n)
    {
        name = n;
    }

    public String getName()
    {
        return name;
    }
}

package ast;
import java.util.*;

public class Block extends Statement
{
    private List<Statement> stmts;

    public Block(List<Statement> s)
    {
        stmts = s;
    }
}

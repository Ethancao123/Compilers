package ast;

/**
 * Class for procedure declarations in a Pascal compiler
 * @author Ethan Cao
 * @version 10/27/21
 */
public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmts;

    /**
     * Constructor for objects of the ProcedureDeclaration class
     * @param n the name of the procedure
     * @param s the statements within the procedure
     */
    public ProcedureDeclaration(String n, Statement s)
    {
        name = n;
        stmts = s;
    }

    /**
     * Executes the procedure declaration
     * @param env the environemnt to execute the declaration in
     */
    public void exec(Environment env)
    {
        env.setProcedure(name,stmts);
    }
}

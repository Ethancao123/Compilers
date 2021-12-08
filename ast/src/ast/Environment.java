package ast;

import java.util.*;

/**
 * Environment to handle variables for a Pascal compiler
 * @author Ethan Cao
 * @version 10/21/21
 */
public class Environment 
{

    private Map<String, Integer> variables = new HashMap<String, Integer>();
    private Map<String, ProcedureDeclaration> procedures 
            = new HashMap<String, ProcedureDeclaration>();
    private Environment parent;

    /**
     * Default constructor for Objects of the Environment class
     */
    public Environment()
    {
        parent = null;
    }

    /**
     * Constructor for Objects of the Environment class
     * @param parent the parent environment
     */
    public Environment(Environment parent)
    {
        this.parent = parent;
    }

    /**
     * Associates the given variable name with the given value
     * @param variable The name of the variable
     * @param value The value of the variable
     */
    public void setVariable(String variable, int value)
    {         
        if(this.hasVariable(variable))
        {
            variables.put(variable, value);
        }
        else if(parent != null && parent.hasVariable(variable))
            parent.declareVariable(variable, value);
        else
            declareVariable(variable, value);
    }

    /**
     * Declares a variable in the local environment
     * @param variable the name of the variable
     * @param value the value of the variable
     */
    public void declareVariable(String variable, int value)
    {
        variables.put(variable, value);
    }
    /**
     * Getter for variables in the Environment
     * @param variable name of the variable to get from the environment
     * @return the variable 
     */
    public int getVariable(String variable)
    {
        if(variables.get(variable) != null)
            return variables.get(variable);
        if(parent != null)
            return parent.getVariable(variable);
        throw new IllegalArgumentException("Variable does not exist in environment");
    }

    /**
     * Checks if a variable with a certain name has been declared previously
     * @param var the variable name to be checked
     * @return true if the variable exists; Otherwise, false
     */
    public boolean hasVariable(String var)
    {
        return variables.get(var) != null;
    }

    /**
     * Adds a procedure to the environment
     * @param name the name of the procedure
     * @param stmts the ProcedureDeclarations within the procedure
     */
    public void setProcedure(String name, ProcedureDeclaration stmts)
    {
        if(parent == null)
        {
            procedures.put(name, stmts);
        }
        else
        {
            parent.setProcedure(name, stmts);
        }
    }

    /**
     * Gets a procedure from the environment
     * @param name the name of the procedure
     * @return the procedure with name
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        if(parent == null)
        {
            return procedures.get(name);
        }
        return parent.getProcedure(name);
    }

    /**
     * Checks if a procedure exists within the environment
     * @param name The name of the procedure
     * @return True if the procedure has been declared; Otherwise, false
     */
    public boolean hasProcedure(String name)
    {
        if(parent == null)
        {
            return procedures.get(name) != null;
        }
        return parent.hasProcedure(name);
    }

    public void compile(Emitter e)
    {
        for(Map.Entry<String,ProcedureDeclaration> entry : procedures.entrySet())
            entry.getValue().compile(e);
    }
}

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

    /**
     * Associates the given variable name with the given value
     * @param variable The name of the variable
     * @param value The value of the variable
     */
    public void setVariable(String variable, int value)
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
        return variables.get(variable);
    }

    /**
     * Checks if a variable with a certain name has been declared previously
     * @param var the variable name to be checked
     * @return true if the variable exists; Otherwise, false
     */
    public boolean hasVariable(String var)
    {
        return variables.get(var) == null;
    }
}

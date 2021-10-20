package ast;

import java.util.*;

public class Environment {

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

    public int getVariable(String variable)
    {
        return variables.get(variable);
    }

    public boolean hasVariable(String variable)
    {
        return variables.get(variable) == null;
    }
}

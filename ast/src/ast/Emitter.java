package ast;
import java.io.*;

/**
 * File Emitter for a pascal compiler
 * @author Ethan Cao
 * @version 12/2/21
 */
public class Emitter
{
    private PrintWriter out;
    private int nextLabel = 0;
    private ProcedureDeclaration proc;
    private int excessStackHeight = 0;

    /**
     * creates an emitter for writing to a new file with given name
     * @param outputFileName the name of the file to write
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * prints one line of code to file (with non-labels indented)
     * @param code the string to write to the file
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    /**
     * closes the file.  should be called after all calls to emit.
     */
    public void close()
    {
        out.close();
    }

    /**
     * Pushes the value of reg to the stack
     * @param reg the value to be pushed
     */
    public void emitPush(String reg)
    {
        emit("subu $sp $sp 4");
        emit("sw " + reg + " ($sp)");
        excessStackHeight += 4;
    }

    /**
     * Pops the top of the stack to reg
     * @param reg the register to pop to
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + " ($sp)");
        emit("addu $sp $sp 4");
        excessStackHeight -= 4;
    }

    /**
     * Gets the next avaliable label ID
     * @return a unique number for the label
     */
    public int nextLabelID()
    {
        return nextLabel++;
    }

    public void setProcedureContext(ProcedureDeclaration procedure)
    {
        proc = procedure;
        excessStackHeight = 0;
    }

    public void clearProcedureContext()
    {
        proc = null;
    }

    public boolean isLocalVariable(String varName)
    {
        if(proc == null)
            return false;
        if(proc.getName().equals(varName))
            return true;
        return proc.getArgs().contains(varName);
    }

    public int getOffset(String varName)
    {
        if(proc == null)
            return -1;
        return excessStackHeight + (proc.getArgs().size() - proc.getArgs().indexOf(varName)) * 4;
    }
}

import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author
 *  
 * Usage:
 * <Insert a comment that shows how to use this object>
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }
    /**
     * Method: getNextChar
     */
    private void getNextChar()
    {

    }
    /**
     * Method: eat
     */
    
    private void eat(char expected) throws ScanErrorException
    {
        
    }
    /**
     * Method: hasNext
     * @return
     */
    public boolean hasNext()
    {
       
    }
    /**
     * Method: nextToken
     * @return
     */
    public String nextToken() throws ScanErrorException
    {

        
    }    
}

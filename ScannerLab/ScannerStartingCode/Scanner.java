
import java.io.*;
import java.util.regex.*;


/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Ethan Cao
 * @version 8/31/21
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    private static Pattern letter;
    private static Pattern digit;
    private static Pattern operand;
    private static Pattern operandPrefix;
    private static Pattern space;

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
        patternInit();
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
        patternInit();
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
        
    }

    private void patternInit()
    {
        letter = Pattern.compile("[a-zA-Z]");
        digit = Pattern.compile("[0-9]");
        operand = Pattern.compile("['=' '+' '-' '*' '/' '%' '(' ')']");
        operandPrefix = Pattern.compile("['>' '<' ':']");
        space = Pattern.compile("['' '\t' '\r' '\n']");
    }

    /**
     * Gets the next character of an input file and determines if the end 
     * of the input file has been reached.
     */
    private void getNextChar()
    {
        int readResult = -1;
        try 
        {
            readResult = in.read();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (readResult == -1 || (char)readResult == '.')
            {
                eof = true;
            }
            else
            {
                currentChar = (char)readResult;
            }
        }
    }

    /**
     * Checks if the expected next character is the same as the next character. 
     * @param expected the expected next character
     * @throws ScanErrorException when the expected next character 
     * and actual next character differ
     */
    private void eat(char expected) throws ScanErrorException
    {
        if(currentChar == expected)
        {
            getNextChar();
        }
        else
        {
            throw new ScanErrorException("Illegal character - expected <currentChar> and found <char>");
        }
    }

    /**
     * Determines if there are any characters remaining in the input file
     * @return true if the end of file has not been reached; Otherwise, false.
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Method: nextToken
     * @return
     */
    public String nextToken() throws ScanErrorException
    {
        String lexeme = "";
        if(isDigit(currentChar))
        {
            lexeme += currentChar;
            while(hasNext() && !isWhitespace(currentChar))
            {
                getNextChar();
                if(isDigit(currentChar))
                    lexeme += currentChar;
                else
                    throw new ScanErrorException("expected a number");
            }
        }
        if(isLetter(currentChar))
        {
            lexeme += currentChar;
            while(hasNext() && !isWhitespace(currentChar))
            {
                getNextChar();
                if(isDigit(currentChar) || isLetter(currentChar))
                    lexeme += currentChar;
                else
                    throw new ScanErrorException("expected a identifier")
            }
        }
        if(isOperand(currentChar))
        {
            lexeme += currentChar;
            if(current)
            //check if prefix
            //check if operand
            //add to lexeme
        }
        return lexeme;

    }    

    /**
     * Determines if a character is a digit
     * @param input the character to check
     * @return true if input is a digit; Otherwise, false
     */
    public static boolean isDigit(char input)
    {
        Matcher m = digit.matcher("" + input);
        return m.matches();
    }

    /**
     * 
     */
    public static boolean isLetter(char input)
    {
        Matcher m = letter.matcher("" + input);
        return m.matches();
    }

    public static boolean isWhitespace(char input)
    {
        Matcher m = space.matcher("" + input);
        return m.matches();
    }

    public static boolean isOperand(char input)
    {
        Matcher m = operand.matcher("" + input);
        return m.matches();
    }

    public static boolean isOperandPrefix(char input)
    {
        Matcher m = operandPrefix.matcher("" + input);
        return m.matches();
    }

}

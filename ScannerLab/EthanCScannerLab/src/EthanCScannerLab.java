
import java.io.*;
import java.util.regex.*;

import javax.lang.model.util.ElementScanner6;


/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Ethan Cao
 * @version 8/31/21
 */
public class EthanCScannerLab
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    private static Pattern letter;
    private static Pattern digit;
    private static Pattern seperator;
    private static Pattern operand;
    private static Pattern equalsPrefix;
    private static Pattern space;
    private static Pattern equals;

    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public EthanCScannerLab(InputStream inStream)
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
    public EthanCScannerLab(String inString)
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
        seperator = Pattern.compile("['|' | '(' | ')' | '{' | '}' | '[' | ']' | '<' | '>' | '\\' | '.' | ';']");
        operand = Pattern.compile("['+' '-' '*' '/' '!']");
        equalsPrefix = Pattern.compile("['>' '<' ':' '+' '-' '*' '/']");
        //space = Pattern.compile("['' ' ' '\t' '\r' '\n' '\\s']");
        space = Pattern.compile("[\\s]");
        equals = Pattern.compile("['=']");
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
        String prefix = "No Prefix";
        String lexeme = "";
        //is a number
        if(isDigit(currentChar))
        {
            prefix = "NUM";
            lexeme += currentChar;
            getNextChar();
            while(hasNext() && !isWhitespace(currentChar))
            {
                if(isDigit(currentChar))
                    lexeme += currentChar;
                else
                    throw new ScanErrorException("expected a number");
                getNextChar();
            }
        }
        //is identifier
        else if(isLetter(currentChar))
        {
            prefix = "ID";
            lexeme += currentChar;
            getNextChar();
            while(hasNext() && !isWhitespace(currentChar))
            {
                if(isDigit(currentChar) || isLetter(currentChar))
                    lexeme += currentChar;
                else
                    throw new ScanErrorException("expected a identifier");
                getNextChar();
            }
        }
        //is a math operator
        else if(isEqualsPrefix(currentChar))
        {
            lexeme += currentChar;
            prefix = "MATH";
            getNextChar();
            if(isEquals(currentChar))
            {
                lexeme += currentChar;
                prefix = "EQ";
                getNextChar();
            }
            else if(!isOperand(lexeme.charAt(0)))
                throw new ScanErrorException("expected an equals sign");
        }
        else if(isEquals(currentChar))
        {
            lexeme += currentChar;
            prefix = "EQ";
            getNextChar();
        }
        else if(isSeperator(currentChar))
        {
            lexeme += currentChar;
            prefix = "SEP";
            getNextChar();
        }
        else
        {
            getNextChar();
            return "";
        }
        if(lexeme.equals(" "))
            return "";
        lexeme = lexeme.strip();
        return "" + prefix + " : " + lexeme;
    }    

    /**
     * 
     */
    public static boolean isLetter(char input)
    {
        Matcher m = letter.matcher("" + input);
        return m.matches();
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

    public static boolean isSeperator(char input)
    {
        Matcher m = seperator.matcher("" + input);
        return m.matches();
    }

    public static boolean isOperand(char input)
    {
        Matcher m = operand.matcher("" + input);
        return m.matches();
    }

    public static boolean isEqualsPrefix(char input)
    {
        Matcher m = equalsPrefix.matcher("" + input);
        return m.matches();
    }

    public static boolean isWhitespace(char input)
    {
        Matcher m = space.matcher("" + input);
        return m.matches();
    }

    public static boolean isEquals(char input)
    {
        Matcher m = equals.matcher("" + input);
        return m.matches();
    }
}

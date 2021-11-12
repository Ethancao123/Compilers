
import java.io.*;
import java.util.regex.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Ethan Cao
 * @version 9/7/21
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

    /**
     * Compiles all the patterns for the Java Regex library
     */
    private void patternInit()
    {
        letter = Pattern.compile("[a-zA-Z]");
        digit = Pattern.compile("[0-9]");
        seperator = Pattern.compile("['|'|'('|')'|'{'|'}'|'['|']'|'<'|'>'|'\\'|'.'|';']");
        operand = Pattern.compile("['>' '<' '+' '*' '/' '!']");
        equalsPrefix = Pattern.compile("['>' '<' ':' '+' '*' '/']");
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
        //if(eof)
            //throw new ScanErrorException("End of file has been reached");
        if(currentChar == expected)
        {
            getNextChar();
        }
        else
        {
            String error = "Illegal character - expected " + currentChar + " and found " + expected;
            throw new ScanErrorException(error);
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
     * Gets the next token in the file
     * @return the next token in the file
     * @throws ScanErrorException When invalid text is found
     */
    public String nextToken() throws ScanErrorException
    {
        if(isWhitespace(currentChar))
        {
            while(hasNext() && isWhitespace(currentChar))
                eat(currentChar);
        }
        if(currentChar == '-')
        {
            eat(currentChar);
            return "MATH : -";
        }
        if(currentChar == ';')
        {
            eat(currentChar);
            return "SEP : ;";
        }
        if(currentChar == ')')
        {
            eat(currentChar);
            return "SEP : )";
        }
        if(currentChar == '(')
        {
            eat(currentChar);
            return "SEP : (";
        }
        if(currentChar == ',')
        {
            eat(currentChar);
            return "SEP : ,";
        }
        String prefix = "No Prefix";
        String lexeme = "";
        //is a number
        if(currentChar != ',' && isDigit(currentChar))
        {
            prefix = "NUM";
            lexeme += currentChar;
            eat(currentChar);
            while(currentChar != ',' && hasNext() && !isWhitespace(currentChar) 
                && !isSeperator(currentChar))
            {
                if(isDigit(currentChar))
                    lexeme += currentChar;
                else
                    throw new ScanErrorException("expected a number found " + currentChar);
                eat(currentChar);
            }
        }
        //is identifier
        else if(isLetter(currentChar))
        {
            prefix = "ID";
            lexeme += currentChar;
            eat(currentChar);
            while(hasNext() && (isDigit(currentChar) || isLetter(currentChar)))
            {
                lexeme += currentChar;
                eat(currentChar);
            }
        }
        //is a math operator
        else if(isEqualsPrefix(currentChar))
        {
            lexeme += currentChar;
            prefix = "MATH";
            eat(currentChar);
            //is an equals operator
            if(isEquals(currentChar))
            {
                lexeme += currentChar;
                prefix = "EQ";
                eat(currentChar);
            }
            //is a seperator
            else if(isSeperator(lexeme.charAt(0)))
            {
                lexeme += currentChar;
                prefix = "SEP";
                eat(currentChar);
            }
        }
            //is a whitespace
        else if(lexeme.equals(" "))
            return "";
        lexeme = lexeme.strip();
        return "" + prefix + " : " + lexeme;
        
    }    

    /**
     * Determines if a character is a letter
     * @param input the character to checked
     * @return true if input is a letter; Otherwise, false
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

    /**
     * Determines if a character is a seperator
     * @param input the character to check
     * @return true if input is a seperator; Otherwise, false
     */
    public static boolean isSeperator(char input)
    {
        Matcher m = seperator.matcher("" + input);
        return m.matches();
    }

    /**
     * Determines if a character is an operand
     * @param input the character to check
     * @return true if input is a operand; Otherwise, false
     */
    public static boolean isOperand(char input)
    {
        Matcher m = operand.matcher("" + input);
        return m.matches();
    }

    /**
     * Determiens if a character is an equals prefix
     * @param input the character to check
     * @return true if input is an equals prefix; Otherwise, false
     */
    public static boolean isEqualsPrefix(char input)
    {
        Matcher m = equalsPrefix.matcher("" + input);
        return m.matches();
    }

    /**
     * Determiens if a character is a whitespace
     * @param input the character to check
     * @return true if input is a whitespace; Otherwise, false
     */
    public static boolean isWhitespace(char input)
    {
        Matcher m = space.matcher(String.valueOf(input));
        return m.matches();
    }

    /**
     * Determiens if a character is an equals sign
     * @param input the character to check
     * @return true if input is a equals sign; Otherwise, false
     */
    public static boolean isEquals(char input)
    {
        Matcher m = equals.matcher("" + input);
        return m.matches();
    }
}

/**
 * Parser for a Pascal Compiler
 * 
 * @author Ethan Cao
 * @version 9/23/21
 */
public class Parser 
{
    private EthanCScannerLab scanner;
    private String currentToken;
    
    /**
     * Constructor for objects of the Parser Class
     * @param scan the scanner to request tokens from
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Parser(EthanCScannerLab scan) throws ScanErrorException
    {
        scanner = scan;
        currentToken = scanner.nextToken();
    }

    /**
     * Checks if the expected next String is the same as the actual next String 
     * and consumes one token if they are equal
     * @param expected the expected next String
     * @throws IllegalArgumentException if the Strings are not equal
     * @throws ScanErrorException when an illegal character is scanned
     */
    private void eat(String expected) throws IllegalArgumentException, ScanErrorException
    {
        if(expected.equals(currentToken))
        {
            System.out.println("eaten: " + currentToken);
            currentToken = scanner.nextToken();
            while(currentToken.trim().isEmpty() && scanner.hasNext())
            {
                System.out.println("eaten: " + currentToken);
                currentToken = scanner.nextToken();
            }
        }
        else
        {
            throw new IllegalArgumentException("Expected " + currentToken + " found " + expected);
        }
    }

    /**
     * Parses an if statement
     * @precondition current token begins an IF statement 
     * @postcondition all tokens in statement have been 
     *               eaten; current token is first one 
     *               after the IF statement 
     * @throws ScanErrorException If an illegal character is scanned
     */
    private void parseIf() throws ScanErrorException
    { 
        eat("ID : IF"); 
        while(!currentToken.equals("SEP : ;"))
            eat(currentToken);
        eat("SEP : ;");
    }

    /**
     * Parses a number
     * @precondition current token is an integer
     * @postcondition number token has been eaten
     * @return the value of the parsed integer
     * @throws ScanErrorException if an invalid value is scanned
     */
    private int parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(currentToken.substring(6)); //removes prefix
        eat(currentToken);
        return num;
    }

    /**
     * Parses a WRITELN Statement and prints the number being written
     * @precondition current token is a statement
     * @postcondition statement token has been eaten
     * @return the result of the statement
     * @throws ScanErrorException if an invalid value is scanned
     */
    public int parseStatement() throws ScanErrorException
    {
        eat("ID : WRITELN");
        eat("SEP : (");
        int returned = (parseTerm());
        eat("SEP : )");
        eat("SEP : ;");
        return returned;
    }

    /**
     * Parses a factor
     * @precondition current token is a factor
     * @postcondition factor token has been eaten
     * @return the value of the factor
     * @throws ScanErrorException if an invalid character is scanned
     */
    public int parseFactor() throws ScanErrorException
    {
        if(currentToken.substring(0,3).equals("NUM"))
        {
            return parseNumber();
        }
        if(currentToken.equals("SEP : ("))
        {
            eat(currentToken);
            int returned = parseTerm();
            eat("SEP : )");
            return returned;
        }
        if(currentToken.equals("MATH : -"))
        {
            eat(currentToken);
            return parseFactor() * -1;
        }
        eat(currentToken);
        return 0;
    }

    /**
     * Parses a term 
     * @precondition the current token is a term
     * @postcondition term tokens have been eaten
     * @return the result of the term math
     * @throws ScanErrorException if an invalid value is scanned
     */
    public int parseTerm() throws ScanErrorException
    {
        int total = parseFactor();
        while(true)
        {
            if(currentToken.equals("MATH : *"))
            {
                eat(currentToken);
                total *= parseFactor();
            }
            else if(currentToken.equals("MATH : /"))
            {
                eat(currentToken);
                total /= parseFactor();
            }
            else
                break;
        }
        return total;
    }
}
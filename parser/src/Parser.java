import java.util.*;

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
    private Map<String, Integer> variables = new HashMap<String, Integer>();

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
        if(scanner.hasNext())
        {
            if(expected.equals(currentToken))
            {
                //System.out.println("eaten: " + currentToken);
                currentToken = scanner.nextToken();
                while(currentToken.trim().isEmpty() && scanner.hasNext())
                {
                    //System.out.println("eaten: " + currentToken);
                    currentToken = scanner.nextToken();
                }
            }
            else
            {
                String error = "Expected " + currentToken + " found " + expected;
                throw new IllegalArgumentException(error);
            }
        }
    }

    /**
     * Parses an if statement
     * @precondition current token begins an IF statement 
     * @postcondition all tokens in statement have been 
     *               eaten; current token is first one 
     *               after the IF statement 
     * @throws ScanErrorException when an illegal character is scanned
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
     * @throws ScanErrorException when an illegal character is scanned
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
     * @throws ScanErrorException when an illegal character is scanned
     */
    public void parseStatement() throws ScanErrorException
    {
        try 
        {
            eat("ID : BEGIN");
            parseStatements();
            eat("ID : END");
            eat("SEP : ;");
        } 
        catch (Exception e) 
        {
            try 
            {
                eat("ID : WRITELN");
                eat("SEP : (");
                int returned = (parseExpression());
                eat("SEP : )");
                eat("SEP : ;");
                System.out.println(returned);
            } 
            catch (Exception ee) 
            {
                parseAssignment();
            }
            
        } 
    }

    /**
     * Parses a factor
     * @precondition current token is a factor
     * @postcondition factor token has been eaten
     * @return the value of the factor
     * @throws ScanErrorException when an illegal character is scanned
     */
    public int parseFactor() throws ScanErrorException
    {
        if(currentToken.substring(0,3).equals("NUM"))
        {
            return parseNumber();
        }
        else if(currentToken.equals("SEP : ("))
        {
            eat(currentToken);
            int returned = parseExpression();
            eat("SEP : )");
            return returned;
        }
        else if(currentToken.equals("MATH : -"))
        {
            eat(currentToken);
            return parseFactor() * -1;
        }
        else if(currentToken.substring(0,2).equals("ID") && variables.get(currentToken) != null)
        {
            String temp = currentToken;
            eat(currentToken);
            return variables.get(temp);
        }
        eat(currentToken);
        return 0;
    }

    /**
     * Parses a term 
     * @precondition the current token is a term
     * @postcondition term tokens have been eaten
     * @return the result of the term math
     * @throws ScanErrorException when an illegal character is scanned
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

    /**
     * Parses an Expression 
     * @precondition the current token is an expression
     * @postcondition expression tokens have been eaten
     * @return the result of the expression
     * @throws ScanErrorException when an illegal character is scanned
     */
    public int parseExpression() throws ScanErrorException
    {
        int total = 0;
        try 
        {
            total = parseTerm();
        } 
        catch (Exception e) 
        {
            return total;
        }
        while(true)
        {
            if(currentToken.equals("MATH : +"))
            {
                eat(currentToken);
                total += parseTerm();
            }
            else if(currentToken.equals("MATH : -"))
            {
                eat(currentToken);
                total -= parseTerm();
            }
            else
                break;
        }
        return total;
    }

    /**
     * Parses multiple statements
     * @precondition the curent token is the start of multiple statements
     * @postcondition all the statements have been eaten
     */
    public void parseStatements()
    {
        while(scanner.hasNext())
        {
            try 
            {
                parseStatement();
            } 
            catch (Exception e) 
            {
                break;
            }
        }
    }

    /**
     * Parses an assignment
     * @precondition the current token is the start of an assignment
     * @postcondition all assignment tokens have been eaten
     * @throws ScanErrorException when an illegal character is scanned
     */
    public void parseAssignment() throws ScanErrorException
    {
        String temp = currentToken;
        eat(currentToken);
        eat("EQ : :=");
        variables.put(temp, parseExpression());
        //System.out.println(variables);
        eat("SEP : ;");
    }
}
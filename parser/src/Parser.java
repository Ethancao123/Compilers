import java.util.*;
import ast.*;
import ast.Number;

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
    private Environment env = new Environment();

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
    private Statement parseIf() throws ScanErrorException
    { 
        eat("ID : IF"); 
        boolean result = parseCondition();
        eat("ID : THEN");
        if(result)
        {
            return parseStatement();
        }
        else
        {
            parseStatement(); //do nothing with the return to ignore
        }
    }

    /**
     * Parses a number
     * @precondition current token is an integer
     * @postcondition number token has been eaten
     * @return the value of the parsed integer
     * @throws ScanErrorException when an illegal character is scanned
     */
    private Number parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(currentToken.substring(6)); //removes prefix
        eat(currentToken);
        return new Number(num);
    }

    /**
     * Parses a WRITELN Statement and prints the number being written
     * @precondition current token is a statement
     * @postcondition statement token has been eaten
     * @throws ScanErrorException when an illegal character is scanned
     * @return a Statement object
     */
    public Statement parseStatement() throws ScanErrorException
    {
        Statement returned = null;
        try 
        {
            eat("ID : BEGIN");
            returned = parseStatements();
            eat("ID : END");
            eat("SEP : ;");
        } 
        catch (Exception e) 
        {
            try 
            {
                eat("ID : WRITELN");
                eat("SEP : (");
                Expression exp = parseExpression();
                eat("SEP : )");
                eat("SEP : ;");
                returned = new Writeln(exp);
            } 
            catch (Exception ee) 
            {
                try 
                {
                    parseIf();
                } 
                catch (Exception eeee) 
                {
                    if(!currentToken.equals("ID : END"))
                        returned = parseAssignment();
                }
                
            }
            
        } 
        return returned;
    }

    /**
     * Parses a factor
     * @precondition current token is a factor
     * @postcondition factor token has been eaten
     * @return the value of the factor
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Expression parseFactor() throws ScanErrorException
    {
        //System.out.println("parsing factor");
        Expression returned = null;
        if(currentToken.substring(0,3).equals("NUM"))
        {
            returned = parseNumber();
        }
        else if(currentToken.equals("SEP : ("))
        {
            eat(currentToken);
            returned = parseExpression();
            eat("SEP : )");
        }
        else if(currentToken.equals("MATH : -"))
        {
            eat(currentToken);
            returned = new BinOp("*", parseFactor(), new Number(-1));
        }
        else if(currentToken.substring(0,2).equals("ID") && env.hasVariable(currentToken) 
                && !currentToken.equals("ID : END"))
        {
            String temp = currentToken;
            eat(currentToken);
            returned = new Variable(temp);
        }
        else
            eat(currentToken);
        return returned;
    }

    /**
     * Parses a term 
     * @precondition the current token is a term
     * @postcondition term tokens have been eaten
     * @return the result of the term math
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Expression parseTerm() throws ScanErrorException
    {
        Expression exp1 = null;
        try
        {
            exp1 = parseFactor();
        }
        catch(Exception e)
        {
            return exp1;
        }
        //System.out.println("parsing term");
        Expression exp2 = null;
        String op = "";
        while(true)
        {
            if(currentToken.equals("MATH : *"))
            {
                eat(currentToken);
                op = "*";
                exp2 = parseFactor();
            }
            else if(currentToken.equals("MATH : /"))
            {
                eat(currentToken);
                op = "/";
                exp2 = parseFactor();
            }
            else
                break;
        }
        return new BinOp(op, exp1, exp2);
    }

    /**
     * Parses an Expression 
     * @precondition the current token is an expression
     * @postcondition expression tokens have been eaten
     * @return the result of the expression
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Expression parseExpression() throws ScanErrorException
    {
        Expression exp1 = parseTerm();
        Expression exp2 = null;
        String op = "";
        // try 
        // {
        //     exp1 = parseTerm();
        // } 
        // catch (Exception e) 
        // {
        //     return exp1;
        // }
        //System.out.println("parsing expression");
        while(true)
        {
            if(currentToken.equals("MATH : +"))
            {
                eat(currentToken);
                op = "+";
                exp2 = parseTerm();
            }
            else if(currentToken.equals("MATH : -"))
            {
                eat(currentToken);
                op = "-";
                exp2 = parseTerm();
            }
            else
                break;
        }
        return new BinOp(op, exp1, exp2);
    }

    /**
     * Parses multiple statements
     * @precondition the curent token is the start of multiple statements
     * @postcondition all the statements have been eaten
     * @return a Block object
     */
    public Block parseStatements()
    {
        ArrayList<Statement> returned = new ArrayList<Statement>();
        while(scanner.hasNext())
        {
            try 
            {
                returned.add(parseStatement());
            } 
            catch (Exception e) 
            {
                break;
            }
        }
        return new Block(returned);
    }

    /**
     * Parses an assignment
     * @precondition the current token is the start of an assignment
     * @postcondition all assignment tokens have been eaten
     * @throws ScanErrorException when an illegal character is scanned
     * @return an Assignment object
     */
    public Assignment parseAssignment() throws ScanErrorException
    {
        String temp = currentToken;
        System.out.println("assigned var " + temp);
        eat(currentToken);
        eat("EQ : :=");
        Expression exp = parseExpression();
        eat("SEP : ;");
        //System.out.println(variables);
        return new Assignment(temp, exp);
    }
}
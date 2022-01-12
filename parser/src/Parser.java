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
     * Parses a Pascal program
     * @return the parsed program
     * @throws ScanErrorException if an illegal character is scanned
     */
    public Program parseProgram() throws ScanErrorException
    {
        List<Statement> stmts = new ArrayList<Statement>();
        while(scanner.hasNext())
        {
            stmts.add(parseStatement());
        }
        return new Program(env, new Block(stmts));
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
        ////System.out.println("parsing statement");
        Statement returned = null;
        if(currentToken.equals("ID : display"))
        {
            eat(currentToken);
            Expression exp = parseExpression();
            String id = "";
            if(currentToken.equals("ID : read"))
            {
                eat(currentToken);
                id = currentToken.substring(currentToken.indexOf(":") + 2);
                eat(currentToken);
            }
            returned = new Writeln(exp, id);
        }
        else if (currentToken.equals("ID : assign"))
            returned = parseAssignment();
        else if(currentToken.equals("ID : while"))
            returned = parseWhile();
        else if(currentToken.equals("ID : if"))
            returned = parseIf();
        else
            throw new ScanErrorException("Invalid statement parse");
        return returned;
    }

    /**
     * Parses a block of statements
     * @return the parsed block of statements
     * @throws ScanErrorException when an illegal token is scanned
     */
    public Statement parseBlock() throws ScanErrorException
    {
        ArrayList<Statement> stmts = new ArrayList<Statement>();
        while(!currentToken.equals("ID : end"))
        {
            stmts.add(parseStatement());
        }
        return new Block(stmts);
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
                    ////System.out.println("eaten: " + currentToken);
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
        eat("ID : if"); 
        Expression c = parseExpression();
        eat("ID : then");
        ArrayList<Statement> stmts = new ArrayList<Statement>();
        while(!currentToken.equals("ID : end") && !currentToken.equals("ID : else"))
        {
            stmts.add(parseStatement());
        }
        Statement elseStatement = null;
        if(currentToken.equals("ID : else"))
        {
            eat(currentToken);
            elseStatement = parseBlock();
        }
        eat("ID : end");
        return new If(new Block(stmts), c, elseStatement);
    }

    /**
     * Parses a while statement
     * @return a while object with the statement
     * @throws ScanErrorException if an illegal character is scanned
     */
    private Statement parseWhile() throws ScanErrorException
    {
        ////System.out.println("parsing while");
        eat("ID : while"); 
        Expression c = parseExpression();
        eat("ID : do");
        //System.out.println("parsed while");
        Statement returned = new While(parseBlock(), c);
        eat("ID : end");
        return returned;
    }
    //TODO: Fix all documentation

    /**
     * Parses an Expression 
     * @precondition the current token is an expression
     * @postcondition expression tokens have been eaten
     * @return the result of the expression
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Expression parseExpression() throws ScanErrorException
    {
        Expression exp1 = parseAddExpr();
        Expression exp2 = null;
        String c = null;
        while(currentToken.equals("SEP : <") || currentToken.equals("SEP : >") || 
                currentToken.equals("SEP : >=") || currentToken.equals("SEP : <=") || 
                currentToken.equals("SEP : <>") || currentToken.equals("SEP : ="))
        {
            c = currentToken;
            eat(currentToken);
            exp2 = parseAddExpr();
            exp1 = new BinOp(c, exp1, exp2);
        }
        return exp1;
    }

    /**
     * Parses a addition statement
     * @precondition the current token is a term
     * @postcondition term tokens have been eaten
     * @return the result of the term SEP
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Expression parseAddExpr() throws ScanErrorException
    {
        Expression exp1 = parseMultExpr();
        while(currentToken.equals("SEP : +") || currentToken.equals("SEP : -"))
        {
            if(currentToken.equals("SEP : +"))
            {
                eat("SEP : +");
                exp1 = new BinOp("+", exp1, parseAddExpr());
            }
            else if(currentToken.equals("SEP : -"))
            {
                eat("SEP : -");
                exp1 = new BinOp("-", exp1, parseAddExpr());
            }
        }
        return exp1;
    }

    /**
     * Parses a multiplication statement
     * @precondition current token is a factor
     * @postcondition factor token has been eaten
     * @return the value of the factor
     * @throws ScanErrorException when an illegal character is scanned
     */
    public Expression parseMultExpr() throws ScanErrorException
    {
        Expression exp1 = parseNegExpr();
        while(currentToken.equals("SEP : *") || currentToken.equals("SEP : /"))
        {
            if(currentToken.equals("SEP : *"))
            {
                eat("SEP : *");
                exp1 = new BinOp("*", exp1, parseNegExpr());
            }
            else if(currentToken.equals("SEP : /"))
            {
                eat("SEP : /");
                exp1 = new BinOp("/", exp1, parseNegExpr());
            }
        }
        return exp1;
    }

    /**
     * Parses a number's sign
     * @return a parsed sign
     * @throws ScanErrorException if an illegal character is scanned
     */
    public Expression parseNegExpr() throws ScanErrorException
    {
        if(currentToken.equals("SEP : -"))
        {
            eat(currentToken);
            return new BinOp("*", parseValue(), new Number(-1));
        }
        return parseValue();
    }

    /**
     * Parses a value
     * @precondition current token is an integer
     * @postcondition number token has been eaten
     * @return the value of the parsed integer
     * @throws ScanErrorException when an illegal character is scanned
     */
    private Expression parseValue() throws ScanErrorException
    {
        Expression returned = null;
        if(currentToken.substring(0,3).equals("NUM"))
        {
            int num = Integer.parseInt(currentToken.substring(6));
            eat(currentToken);
            returned = new Number(num);
        }
        else if(currentToken.equals("SEP : ("))
        {
            eat(currentToken);
            returned = parseExpression();
            eat("SEP : )");
        }
        else if(currentToken.substring(0,2).equals("ID"))
        {
            returned = new Variable(currentToken);
            eat(currentToken);
        }
        return returned;

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
        //System.out.println("parsing assignment");
        eat("ID : assign");
        if(env.hasVariable(currentToken))
            System.out.println(currentToken + " Already exists");
        String temp = currentToken;
        //System.out.println("assigned var " + temp);
        eat(currentToken);
        eat("SEP : =");
        Expression exp = parseExpression();
        return new Assignment(temp, exp);
    }
}
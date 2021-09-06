package scanner;

/**
* This file defines a simple lexer for the compilers course
*
* @author  Ethan Cao
* @version 9/6/2021
* 
*/

import java.io.*;


%%
/* lexical functions */
/* specify that the class will be called Scanner and the function to get the next
 * token is called nextToken.  
 */
%class Scanner
%unicode
%line
%public
%function nextToken
/*  return String objects - the actual lexemes */
/*  returns the String "END: at end of file */
%type String
%eofval{
return "END";
%eofval}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* use switch statement to encode DFA */
//%switch

/**
 * Pattern definitions
 */
Number = "0" | [1-9][0-9]*
Identifier = [a-zA-Z][a-zA-Z0-9_]*
Seperator = "|" | "(" | ")" | "{" | "}" | "[" | "]" | "<" | ">" | "\\" | "." | ";"
Math = "*" | "+" | "-" | "/" | "!"
EqualsPrefix = ":" | ">" | "<" | {Math}
Equals = {EqualsPrefix}"=" | "="

%%
/**
 * lexical rules
 */
[a-zA-Z][a-zA-Z0-9_]*	{return "ID: " + yytext();}
[0-9]+			{return "NUM:" + yytext();}
{Seperator}           {return "SEP: " + yytext();}
{Math}          {return "MATH: " + yytext();}
{Equals}            {return "EQ: " + yytext();}
{WhiteSpace}		{}
.			{ /* do nothing */ }

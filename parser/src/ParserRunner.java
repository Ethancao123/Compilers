import java.io.*;

import ast.Environment;

public class ParserRunner {
    public static void main(String[] args) throws Exception {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        Environment env = new Environment();
        parse.parseStatement().exec(env);
    }
}


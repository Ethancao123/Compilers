import java.io.*;

public class ParserRunner {
    public static void main(String[] args) throws Exception {
        Parser parse = new Parser(new EthanCScannerLab(new FileInputStream("parser/test.txt")));
        parse.parseProgram().exec();
    }
}


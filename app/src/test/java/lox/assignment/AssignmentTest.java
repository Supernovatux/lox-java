package lox.assignment;

import org.junit.jupiter.api.Test;

import lox.*;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest extends templateClass {
    @Test
    void testAssociativity() throws Exception {
        captureOut();
        String[] args = argGen("assignment/associativity.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                c
                c
                c""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testGlobalVar() throws Exception {
        captureOut();
        String[] args = argGen("assignment/global.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                before
                after
                arg
                arg""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testGrouping() throws Exception {
        captureErr();
        Lox.main(argGen("assignment/grouping.lox",true));
        String theOutput = getErr();
        assertEquals("[line 2] Error at '=': Invalid assignment target.", theOutput.trim().strip());
    }
    @Test
    void testInfixOperator() throws Exception {
        captureErr();
        Lox.main(argGen("assignment/infix_operator.lox",true));
        String theOutput = getErr();
        assertEquals("[line 3] Error at '=': Invalid assignment target.", theOutput.trim().strip());
    }
    @Test
    void testLocal() throws Exception {
        captureOut();
        Lox.main(argGen("assignment/local.lox",true));
        String theOutput = getOut();
        assertEquals("before\n" +
                "after\n" +
                "arg\n" +
                "arg", theOutput.trim().strip());
    }
    @Test
    void testPrefixOperator() throws Exception {
        captureErr();
        Lox.main(argGen("assignment/prefix_operator.lox",true));
        String theOutput = getErr();
        assertEquals("[line 2] Error at '=': Invalid assignment target.", theOutput.trim().strip());
    }
    @Test
    void testSyntax() throws Exception {
        captureOut();
        Lox.main(argGen("assignment/syntax.lox",true));
        String theOutput = getOut();
        assertEquals("var\n" +
                "var", theOutput.trim().strip());
    }
    //Todo: Add more tests
}

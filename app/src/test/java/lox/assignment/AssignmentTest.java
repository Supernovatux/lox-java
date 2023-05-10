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
}

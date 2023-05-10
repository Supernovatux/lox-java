package lox.If;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfTest extends templateClass {
    //Todo: Add tests for if
    @Test
    void testDanglingElse() throws Exception {
        captureOut();
        String[] args = argGen("if/dangling_else.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "good";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testElse() throws Exception {
        captureOut();
        String[] args = argGen("if/else.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                good
                good
                block""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testIf() throws Exception {
        captureOut();
        String[] args = argGen("if/if.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                good
                block
                true""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testTruth() throws Exception {
        captureOut();
        String[] args = argGen("if/truth.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                false
                nil
                true
                0
                empty""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testVarElse() throws Exception {
        captureErr();
        String[] args = argGen("if/var_in_else.lox",true);
        Lox.main(args);
        String theOutput = getErr();
        String expected = "[line 2] Error at 'var': Expect expression.";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testVarThen() throws Exception {
        captureErr();
        String[] args = argGen("if/var_in_then.lox",true);
        Lox.main(args);
        String theOutput = getErr();
        String expected = "[line 2] Error at 'var': Expect expression.";
        assertEquals(expected, theOutput.strip().trim());
    }
}

package lox.forLoop;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForLoopTest extends templateClass {
    //Todo: Add tests for for loop
    @Test
    void testForScope() throws Exception {
        captureOut();
        String[] args = argGen("for/scope.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "0\n" +
                "-1\n" +
                "after\n" +
                "0";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testStatementCondition() throws Exception {
        captureErr();
        String[] args = argGen("for/statement_condition.lox",true);
        Lox.main(args);
        String theOutput = getErr();
        String expected = "[line 3] Error at '{': Expect expression.\n" +
                "[line 3] Error at ')': Expect ';' after expression.";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testStatementIncrement() throws Exception {
        captureErr();
        String[] args = argGen("for/statement_increment.lox",true);
        Lox.main(args);
        String theOutput = getErr();
        String expected = "[line 2] Error at '{': Expect expression.";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testStatementIntializer() throws Exception {
        captureErr();
        String[] args = argGen("for/statement_initializer.lox",true);
        Lox.main(args);
        String theOutput = getErr();
        String expected = "[line 3] Error at '{': Expect expression.\n" +
                "[line 3] Error at ')': Expect ';' after expression.";
        assertEquals(expected, theOutput.strip().trim());
    }
    //Todo: Add syntax test
    @Test
    void testVarInBody() throws Exception {
        captureErr();
        String[] args = argGen("for/var_in_body.lox",true);
        Lox.main(args);
        String theOutput = getErr();
        String expected = "[line 2] Error at 'var': Expect expression.";
        assertEquals(expected, theOutput.strip().trim());
    }

}

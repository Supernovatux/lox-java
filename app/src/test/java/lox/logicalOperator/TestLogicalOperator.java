package lox.logicalOperator;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogicalOperator extends templateClass {
    @Test
    void testAnd() throws Exception {
        captureOut();
        String[] args = argGen("logical_operator/and.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                false
                1
                false
                true
                3
                true
                false""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testAndTruth() throws Exception {
        captureOut();
        String[] args = argGen("logical_operator/and_truth.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                false
                nil
                ok
                ok
                ok""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testOr() throws Exception {
        captureOut();
        String[] args = argGen("logical_operator/or.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                1
                1
                true
                false
                false
                false
                true""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testOrTruth() throws Exception {
        captureOut();
        String[] args = argGen("logical_operator/or_truth.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                ok
                ok
                true
                0
                s""";
        assertEquals(expected, theOutput.strip().trim());
    }
}


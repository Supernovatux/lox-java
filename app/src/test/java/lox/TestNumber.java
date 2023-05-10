package lox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestNumber extends templateClass{
    @Test
    void testLiterals() throws Exception {
        captureOut();
        Lox.main(argGen("number/literals.lox"));
        String theOutput = getOut();
        String expected = """
                123
                987654
                0
                -0
                123.456
                -0.001""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testNanEquality() throws Exception {
        captureOut();
        Lox.main(argGen("number/nan_equality.lox"));
        String theOutput = getOut();
        String expected = """
                false
                true
                false
                true""";
        assertEquals(expected, theOutput.strip().trim());
    }
}

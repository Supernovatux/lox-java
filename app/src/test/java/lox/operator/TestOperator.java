package lox.operator;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOperator extends templateClass {
    @Test
    void testAddBoolNil() throws Exception {
        captureErr();
        Lox.main(argGen("operator/add_bool_nil.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be two numbers or two strings.\n" +
                "[line 1]", theOutput.trim().strip());
    }

    @Test
    void testAddBoolNum() throws Exception {
        captureErr();
        Lox.main(argGen("operator/add_bool_num.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be two numbers or two strings.\n" +
                "[line 1]", theOutput.trim().strip());
    }

    @Test
    void testAddBoolString() throws Exception {
        captureErr();
        Lox.main(argGen("operator/add_bool_string.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be two numbers or two strings.\n" +
                "[line 1]", theOutput.trim().strip());
    }

    @Test
    void testAdd() throws Exception {
        captureOut();
        Lox.main(argGen("operator/add.lox"));
        String theOutput = getOut();
        String expected = """
                579
                string""";
        assertEquals(expected, theOutput.strip().trim());
    }

    @Test
    void testAddNilNil() throws Exception {
        captureErr();
        Lox.main(argGen("operator/add_nil_nil.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be two numbers or two strings.\n" +
                "[line 1]", theOutput.trim().strip());
    }

    @Test
    void testAddNumNil() throws Exception {
        captureErr();
        Lox.main(argGen("operator/add_num_nil.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be two numbers or two strings.\n" +
                "[line 1]", theOutput.trim().strip());
    }

    @Test
    void testAddStringNil() throws Exception {
        captureErr();
        Lox.main(argGen("operator/add_string_nil.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be two numbers or two strings.\n" +
                "[line 1]", theOutput.trim().strip());
    }

    @Test
    void testComparison() throws Exception {
        captureOut();
        Lox.main(argGen("operator/comparison.lox"));
        String theOutput = getOut();
        String expected = """
                true
                false
                false
                true
                true
                false
                false
                false
                true
                false
                true
                true""";
        assertEquals(expected, theOutput.strip().trim());
    }

    @Test
    void testDivide() throws Exception {
        captureOut();
        Lox.main(argGen("operator/divide.lox"));
        String theOutput = getOut();
        String expected = """
                4
                1""";
        assertEquals(expected, theOutput.strip().trim());
    }

    @Test
    void testDivideNonNumNum() throws Exception {
        captureErr();
        Lox.main(argGen("operator/divide_nonnum_num.lox", true));
        String theOutput = getErr();
        assertEquals("Operands must be numbers.\n" +
                "[line 1]", theOutput.trim().strip());
    }
    @Test
    void testEquals() throws Exception {
        captureOut();
        Lox.main(argGen("operator/equals.lox"));
        String theOutput = getOut();
        String expected = """
                true
                true
                false
                true
                false
                true
                false
                false
                false
                false""";
        assertEquals(expected, theOutput.strip().trim());
    }
    //Todo: testEqualsClass

}

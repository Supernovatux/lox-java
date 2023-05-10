package lox.bool;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoolTest extends templateClass {
    @Test
    void testEquality() throws Exception {
        captureOut();
        String[] args = argGen("bool/equality.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                true
                false
                false
                true
                false
                false
                false
                false
                false
                false
                true
                true
                false
                true
                true
                true
                true
                true""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testNot() throws Exception {
        captureOut();
        String[] args = argGen("bool/not.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                false
                true
                true""";
        assertEquals(expected, theOutput.strip().trim());
    }
}
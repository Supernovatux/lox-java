package lox.assignment;

import org.junit.jupiter.api.Test;

import lox.*;

import static org.junit.jupiter.api.Assertions.*;

class associativity extends AppTest {
    @Test
    void Test() throws Exception {
        captureOut();
        String[] args = argGen("assignment/associativity.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "c\n" +
                "c\n" +
                "c";
        assertEquals(expected, theOutput.strip().trim());
    }
}

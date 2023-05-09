package lox.assignment;

import org.junit.jupiter.api.Test;

import lox.*;

import static org.junit.jupiter.api.Assertions.*;

class global extends AppTest {
    @Test
    void Test() throws Exception {
        captureOut();
        String[] args = argGen("assignment/global.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "before\n" +
                "after\n" +
                "arg\n" +
                "arg";
        assertEquals(expected, theOutput.strip().trim());
    }
}

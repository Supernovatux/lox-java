package lox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainParserTest extends templateClass {
    @Test
    void precedenceTest() throws Exception {
        captureOut();
        String[] args = argGen("precedence.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = """
                14
                8
                4
                0
                true
                true
                true
                true
                0
                0
                0
                0
                4""";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testSample() throws Exception {
        captureOut();
        Lox.main(argGen("sample.lox"));
        String theOutput = getOut();
        assertEquals("1", theOutput.trim().strip());
    }
    @Test
    void testUnexpectedCharecter() throws Exception {
        captureErr();
        Lox.main(argGen("unexpected_character.lox",true));
        String theOutput = getErr();
        assertEquals("[line 3] Error: Unexpected character.\n" +
                "[line 3] Error at '(': Expect ';' after expression.", theOutput.trim().strip());
    }
    @Test
    void scannerSanityCheck() {
        Scanner classUnderTest = new Scanner("+");
        assertNotNull(classUnderTest.scanTokens());
    }

}

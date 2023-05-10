package lox.block;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockTest extends templateClass {
    @Test
    void testEmpty() throws Exception {
        captureOut();
        String[] args = argGen("block/empty.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "ok";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testScope() throws Exception {
        captureOut();
        String[] args = argGen("block/scope.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "inner\n"+
                "outer";
        assertEquals(expected, theOutput.strip().trim());
    }
}

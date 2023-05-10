package lox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class template extends templateClass {
  @Test
  public void testNil() throws Exception {
    captureOut();
    Lox.main(argGen("nil/literal.lox"));
    String theOutput = getOut();
    assertEquals("nil", theOutput.trim().strip());
  }
  @Test
  public void testPrint() throws Exception {
    captureErr();
    Lox.main(argGen("print/missing_argument.lox",true));
    String theOutput = getErr();
    assertEquals("[line 2] Error at ';': Expect expression.", theOutput.trim().strip());
  }
}

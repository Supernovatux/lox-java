package lox;

import org.junit.jupiter.api.Test;

class template extends templateClass {
  @Test
  public void test() throws Exception {
    captureOut();
    // Lox.main(argGen("template.lox"));
    String theOutput = getOut();
    // assertEquals("1", theOutput.trim().strip());
  }
}

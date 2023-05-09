package lox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class template extends AppTest {
  @Test
  public void test() throws Exception {
    captureOut();
    // Lox.main(argGen("template.lox"));
    String theOutput = getOut();
    // assertEquals("1", theOutput.trim().strip());
  }
}

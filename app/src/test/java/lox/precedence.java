package lox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class precedence extends AppTest {
  @Test
  void precedenceTest() throws Exception {
    captureOut();
    String[] args = argGen("precedence.lox");
    Lox.main(args);
    String theOutput = getOut();
    String expected = "14\n" +
        "8\n" +
        "4\n" +
        "0\n" +
        "true\n" +
        "true\n" +
        "true\n" +
        "true\n" +
        "0\n" +
        "0\n" +
        "0\n" +
        "0\n" +
        "4";
    assertEquals(expected, theOutput.strip().trim());
  }
}

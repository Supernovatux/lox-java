package lox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class sample extends AppTest {
  @Test
  public void test() throws Exception {
    captureOut();
    Lox.main(argGen("sample.lox"));
    String theOutput = getOut();
    assertEquals("1", theOutput.trim().strip());

  }
}

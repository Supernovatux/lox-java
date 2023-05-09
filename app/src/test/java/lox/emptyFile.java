package lox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class emptyFile extends AppTest {
  @Test
  public void test() throws Exception {
    captureOut();
    Lox.main(argGen("empty_file.lox"));
    String theOutput = getOut();
    assertEquals("", theOutput.trim().strip());
  }
}

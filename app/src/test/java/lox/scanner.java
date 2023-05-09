package lox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class scanner extends AppTest {
  @Test
  void scannerSanityCheck() {
    Scanner classUnderTest = new Scanner("+");
    assertNotNull(classUnderTest.scanTokens());
  }

}

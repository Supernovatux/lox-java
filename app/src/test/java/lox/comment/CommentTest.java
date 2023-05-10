package lox.comment;

import lox.Lox;
import lox.templateClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest extends templateClass {
    @Test
    void testLineAtEof() throws Exception {
        captureOut();
        String[] args = argGen("comments/line_at_eof.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "ok";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testLineOnly() throws Exception {
        captureOut();
        String[] args = argGen("comments/only_line_comment.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testEmptyWithLine() throws Exception {
        captureOut();
        String[] args = argGen("comments/only_line_comment_and_line.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "";
        assertEquals(expected, theOutput.strip().trim());
    }
    @Test
    void testUnicodeComment() throws Exception {
        captureOut();
        String[] args = argGen("comments/unicode.lox");
        Lox.main(args);
        String theOutput = getOut();
        String expected = "ok";
        assertEquals(expected, theOutput.strip().trim());
    }
}

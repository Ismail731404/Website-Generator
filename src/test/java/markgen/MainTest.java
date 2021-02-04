/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package markgen;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test public void testFirstMarkdownTitle() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("# Hello world");
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        String parseResult = renderer.render(document);
        String expected = "<h1>Hello world</h1>\n";

        assertEquals(expected, parseResult);
    }
}
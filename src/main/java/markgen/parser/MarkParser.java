package markgen.parser;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MarkParser {

    public MarkParser(){

    }

    /**
     * Read a md file
     * @param path file's path
     * @return InputStreamReader
     *
     */
    public InputStreamReader readFile(String path) throws FileNotFoundException {
        FileInputStream fileIS = new FileInputStream(path);
        return new InputStreamReader(fileIS, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param input md File
     * @return generated html file
     * @throws IOException
     */
    public String mdToHtml(Reader input) throws IOException {
        Parser parser = Parser.builder().build();

        Node document = parser.parseReader(input);
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        return renderer.render(document);
    }

}

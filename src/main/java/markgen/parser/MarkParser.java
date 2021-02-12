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
     *
     * @param path file's path
     * @return InputStreamReader
     */
    public InputStreamReader readFile(String path) throws FileNotFoundException {
        try {
            FileInputStream fileIS = new FileInputStream(path);

            return new InputStreamReader(fileIS, StandardCharsets.UTF_8);
        }catch(FileNotFoundException e){
            throw new FileNotFoundException("File not exists");
        }
    }

    /**
     *
     * @param input md File
     * @return generated html file
     * @throws IOException
     */
    public String mdToHtml(Reader input) throws IOException {
        Parser parser = Parser.builder().build();

        try {

            Node document = parser.parseReader(input);
            HtmlRenderer renderer = HtmlRenderer.builder().build();

            return renderer.render(document);
        } catch (IOException e) {
            throw new IOException("Cannot parse the file");
        }
    }

}

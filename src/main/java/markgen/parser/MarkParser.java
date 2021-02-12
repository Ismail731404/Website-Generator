package markgen.parser;

import org.commonmark.Extension;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MarkParser {
    ArrayList<Extension> extensions = new ArrayList<>();

    public MarkParser(){
        initExtensions();
    }

    /**
     * Initialise extensions to support (Tables, links, images,..etc)
     */
    private void initExtensions(){
        extensions.add(StrikethroughExtension.create());
        extensions.add(TablesExtension.create());
        extensions.add(AutolinkExtension.create());
        extensions.add(InsExtension.create());
        extensions.add(ImageAttributesExtension.create());
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
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();

        Node document = parser.parseReader(input);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .build();

        return renderer.render(document);
    }

}

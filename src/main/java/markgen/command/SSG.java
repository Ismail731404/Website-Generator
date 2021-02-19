package markgen.command;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import markgen.html.HtmlGenerator;
import markgen.parser.MarkParser;


@Command(name = "ssg",
    version = "@|yellow 1.0|@",
    sortOptions = false,
    mixinStandardHelpOptions = true,
    header =
    "      ___           ___           ___     \n" +
    "     /\\__\\         /\\__\\         /\\__\\    \n" +
    "    /:/ _/_       /:/ _/_       /:/ _/_   \n" +
    "   /:/ /\\  \\     /:/ /\\  \\     /:/ /\\  \\  \n" +
    "  /:/ /::\\  \\   /:/ /::\\  \\   /:/ /::\\  \\ \n" +
    " /:/_/:/\\:\\__\\ /:/_/:/\\:\\__\\ /:/__\\/\\:\\__\\\n" +
    " \\:\\/:/ /:/  / \\:\\/:/ /:/  / \\:\\  \\ /:/  /\n" +
    "  \\::/ /:/  /   \\::/ /:/  /   \\:\\  /:/  / \n" +
    "   \\/_/:/  /     \\/_/:/  /     \\:\\/:/  /  \n" +
    "     /:/  /        /:/  /       \\::/  /   \n" +
    "     \\/__/         \\/__/         \\/__/    \n",
    footer = "@|bold Copyright (c) 2021|@",
    description = "Files translator."
)
public class SSG implements Runnable { 

    @Option(names = {"build"}, description = "builder") 
    private boolean build;
    
    @Parameters(arity = "1..*", paramLabel = "file0.html...fileN.html", description = "HTML files to product.")
    private File[] files;

    @Option(names = "--output-dir", description = "HTML files product in specified directory.")
    private String path = "";

    @Override
    public void run() { 
        if(build) {
            MarkParser p = new MarkParser();
            try {
                // Read md file
                InputStreamReader reader = p.readFile("test.md");
                // Render md file
                String body = p.mdToHtml(reader);
                // Create instance, Default output directory is '_output'
                // see HtmlGenerator
                HtmlGenerator htmlGenerator = null;
                if(path.length() > 0)
                    htmlGenerator = new HtmlGenerator(path);
                else
                    htmlGenerator = new HtmlGenerator();
                for(int i = 0; i < files.length; i++) {
                    System.out.println("File : " + files[i].getName());
                }
                // Generate a full html page with header, title and body
                String page = htmlGenerator.generate("Output", body);
                // Write generated page to file
                htmlGenerator.write(page, "test.html");

            } catch(IOException e ) {
                e.printStackTrace();
            }
        }
    }
}
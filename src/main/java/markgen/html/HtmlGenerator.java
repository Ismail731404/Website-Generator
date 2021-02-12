package markgen.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class HtmlGenerator {
    /**
     * path of the output directory, where html file will be generated
     */
    private String outputDirectory;

    /**
     *
     * @param outputDirectory
     */
    public HtmlGenerator(String outputDirectory){
        setOutputDirectory(outputDirectory);
    }
    public HtmlGenerator(){
        this("_output");
    }


    public String getOutputDirectory(){
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory){
        this.outputDirectory = outputDirectory;
    }


    /**
     * Generate a full html page , with header and body
     * @param title Title of the document
     * @param body Body of the document
     * @return String, Full html page
     */
    public String generate(String title, String body){
        StringBuilder squelette = new StringBuilder();
        squelette.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("<title>")
                .append(title)
                .append("</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append(body)
                .append("</body>\n</html>\n");

        return squelette.toString();
    }

    /**
     * Create and write generated html into a file
     * @param page Full generated html page {@link #generate(String, String)}
     * @param fileName Name of the generated html file
     */
    public void write(String page, String fileName){
        try {
            // create the outputDirectory if not exists
            File dir = new File(outputDirectory);
            if(! dir.exists()){
                dir.mkdirs();
            }

            FileWriter fileWriter = new FileWriter(outputDirectory+"/"+fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(page);
            bufferedWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


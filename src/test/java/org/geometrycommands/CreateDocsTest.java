package org.geometrycommands;

import org.junit.Test;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

public class CreateDocsTest {

    @Test
    public void createDocs() throws Exception {

        File dir = new File("src/website/source/commands");
        File imageDir = new File("src/website/source/commands");
        String NEW_LINE = System.getProperty("line.separator");

        Properties examples = new Properties();
        File examplesFile = new File("src/test/resources/examples.properties");
        try (InputStream inputStream = new FileInputStream(examplesFile)) {
            examples.load(inputStream);
        }

        ServiceLoader<Command> commands = ServiceLoader.load(Command.class);
        Iterator<Command> iterator = commands.iterator();
        while(iterator.hasNext()) {
            Command cmd = iterator.next();
            File file = new File(dir, cmd.getName() + ".rst");
            File imageFile = new File(imageDir, cmd.getName() + ".png");
            String example = examples.getProperty("geom-" + cmd.getName().toLowerCase());
            String optionsStr = "";
            for (Class c = cmd.getOptions().getClass(); c != null; c = c.getSuperclass()) {
                for(Field fld : c.getDeclaredFields()) {
                    Option opt = fld.getAnnotation(Option.class);
                    if (opt != null)  {
                        optionsStr += "   * " + opt.name();
                        for(String alias : opt.aliases()) {
                            optionsStr += " " + alias;
                        }
                        optionsStr += ": " + opt.usage();
                        optionsStr += NEW_LINE + NEW_LINE;
                    }
                }
            }
            String rst = cmd.getName() + NEW_LINE;
            for(int i=0; i<cmd.getName().length(); i++) {
                rst += "=";
            }
            rst += NEW_LINE;
            rst += NEW_LINE;
            rst += "**Name**:" + NEW_LINE;
            rst += NEW_LINE;
            rst += "geom " + cmd.getName() + NEW_LINE;
            rst += NEW_LINE;
            rst += "**Description**:" + NEW_LINE;
            rst += NEW_LINE;
            rst += "geom " + cmd.getDescription() + NEW_LINE;
            rst += NEW_LINE;
            rst += "**Arguments**:" + NEW_LINE;
            rst += NEW_LINE;
            rst += optionsStr + NEW_LINE;
            rst += NEW_LINE;
            rst += "**Example**::" + NEW_LINE;
            rst += NEW_LINE;
            rst += "    " + example + NEW_LINE;
            rst += NEW_LINE;
            if (imageFile.exists()) {
                rst += ".. image:: " + cmd.getName().toLowerCase() + ".png";
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(rst);
            fileWriter.close();

        }

    }
}
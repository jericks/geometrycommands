package org.geometrycommands;

import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateManPagesTest {

    private static String NEW_LINE = System.getProperty("line.separator");

    private static String removeDate(String content) {
        // Remove date (because that will change)
        String[] lines = content.split(NEW_LINE);
        String firstLine = lines[0].replaceFirst("\\d+ \\w+ \\d\\d\\d\\d", "");
        //firstLine + (1..<lines.size()).collect { lines[it] }.join(NEW_LINE);
        String newContent = firstLine;
        for(int i = 1; i<lines.length; i++) {
            newContent += NEW_LINE + lines[i];
        }
        return newContent;
    }

    @Test
    public void createDocs() throws Exception {

        File dir = new File("src/man");

        Properties examples = new Properties();
        File examplesFile = new File("src/test/resources/examples.properties");
        try (InputStream inputStream = new FileInputStream(examplesFile)) {
            examples.load(inputStream);
        }

        DateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");
        String date = dateFormat.format(new Date());

        String version = "0.8.0";

        boolean overwrite = false;

        ServiceLoader<Command> commands = ServiceLoader.load(Command.class);
        Iterator<Command> iterator = commands.iterator();
        while(iterator.hasNext()) {

            Command cmd = iterator.next();
            File file = new File(dir, "geom-" + cmd.getName() + ".1");

            String oldContents = "";
            if (file.exists()) {
                oldContents = new String(Files.readAllBytes(file.toPath()),"UTF-8");
            }

            String contents = ".TH \"geom-" + cmd.getName() + "\" \"1\" \"4 May 2012\" \"version 0.1\"" + NEW_LINE;
            contents += ".SH NAME" + NEW_LINE;
            contents += "geom " + cmd.getName() + NEW_LINE;
            contents += ".SH DESCRIPTION" + NEW_LINE;
            contents += cmd.getDescription() + NEW_LINE;
            String example = examples.getProperty("geom-" + cmd.getName().toLowerCase());
            if (example != null) {
                contents += ".SH USAGE" + NEW_LINE;
                contents += example + NEW_LINE;
            }

            contents += ".SH OPTIONS" + NEW_LINE;

            for (Class c = cmd.getOptions().getClass(); c != null; c = c.getSuperclass()) {
                for(Field fld : c.getDeclaredFields()) {
                    Option opt = fld.getAnnotation(Option.class);
                    if (opt != null)  {
                        contents += opt.name();
                        for(String alias : opt.aliases()) {
                            contents += " " + alias;
                        }
                        contents += ": " + opt.usage() + NEW_LINE;
                        contents += ".PP" + NEW_LINE;
                    }
                }
            }

            if (!removeDate(contents).equals(removeDate(oldContents))) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(contents);
                fileWriter.close();
            }

        }

    }

}

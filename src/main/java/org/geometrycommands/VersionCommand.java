package org.geometrycommands;

import org.geometrycommands.VersionCommand.VersionOptions;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public class VersionCommand implements Command<VersionOptions> {


    /**
     * Get the Command's name
     *
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "version";
    }

    /**
     * Get the description of what the Command does
     *
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get the version";
    }

    /**
     * Get a new Options
     *
     * @return A new Options
     */
    @Override
    public VersionOptions getOptions() {
        return new VersionOptions();
    }

    /**
     * Execute this Command with the given Options
     *
     * @param options The Options
     * @param reader  The java.io.Reader
     * @param writer  The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(VersionOptions options, Reader reader, Writer writer) throws Exception {
        try(InputStream inputStream = VersionCommand.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            writer.write(properties.getProperty("version"));
        }
    }

    public static class VersionOptions extends Options {
    }

}

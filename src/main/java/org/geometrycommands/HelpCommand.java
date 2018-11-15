package org.geometrycommands;

import org.geometrycommands.HelpCommand.HelpOptions;

import java.io.Reader;
import java.io.Writer;

public class HelpCommand implements Command<HelpOptions> {

    /**
     * Get the Command's name
     *
     * @return The Command's name
     */
    @Override
    public String getName() {
        return "help";
    }

    /**
     * Get the description of what the Command does
     *
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "Get help";
    }

    /**
     * Get a new Options
     *
     * @return A new Options
     */
    @Override
    public HelpOptions getOptions() {
        return new HelpOptions();
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
    public void execute(HelpOptions options, Reader reader, Writer writer) throws Exception {
        final String newLine = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("Usage: geom <command> <args>").append(newLine);
        builder.append(newLine);
        builder.append("'geom list' lists available commands.").append(newLine);
        builder.append(newLine);
        builder.append("Use 'geom <command> --help' for help using a specific command.");
        writer.write(builder.toString());
    }

    public static class HelpOptions extends Options {
    }

}

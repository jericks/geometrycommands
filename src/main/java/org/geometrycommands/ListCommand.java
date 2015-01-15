package org.geometrycommands;

import org.kohsuke.args4j.Option;

import java.io.Reader;
import java.io.Writer;
import java.util.*;
import org.geometrycommands.ListCommand.ListOptions;

/**
 * List all Commands
 *
 * @author Jared Erickson
 */
public class ListCommand implements Command<ListOptions> {

    /**
     * Get the name of the command
     *
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "list";
    }

    /**
     * Get the description of what the Command does
     *
     * @return The description of what the Command does
     */
    @Override
    public String getDescription() {
        return "List all of the geometry commands.";
    }

    /**
     * Get a new Options
     *
     * @return A new Options
     */
    @Override
    public ListOptions getOptions() {
        return new ListOptions();
    }

    /**
     * Get a List of all Geometry Commands
     *
     * @param options The Options
     * @param reader  The java.io.Reader
     * @param writer  The java.io.Writer
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(ListOptions options, Reader reader, Writer writer) throws Exception {
        // Find all of the registered Commands
        final ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        // Collect the Commands
        List<Command> commands = new ArrayList<Command>();
        for (Iterator<Command> it = commandServiceLoader.iterator(); it.hasNext(); ) {
            Command cmd = it.next();
            commands.add(cmd);
        }
        // Sort the Commands by name
        Collections.sort(commands, new Comparator<Command>() {
            @Override
            public int compare(Command c1, Command c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        // Write the names
        String NEW_LINE = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        for (Iterator<Command> it = commands.iterator(); it.hasNext(); ) {
            Command cmd = it.next();
            builder.append(cmd.getName());
            if (options.isIncludingDescription()) {
                builder.append(" = ").append(cmd.getDescription());
            }
            if (it.hasNext()) {
                builder.append(NEW_LINE);
            }
        }
        writer.write(builder.toString());
    }

    /**
     * ListCommand Options
     */
    public static class ListOptions extends Options {

        /**
         * Whether to include the description or not
         */
        @Option(name = "-d", aliases = "--description", usage = "Include the description", required = false)
        private boolean includingDescription = false;

        /**
         * Get whether to include the description or not
         * @return Whether to include the description or not
         */
        public boolean isIncludingDescription() {
            return includingDescription;
        }

        /**
         * Set whether to include the description or not
         * @param includingDescription Whether to include the description or not
         */
        public void setIncludingDescription(boolean includingDescription) {
            this.includingDescription = includingDescription;
        }
    }

}

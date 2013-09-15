package org.geometrycommands;

import java.io.Reader;
import java.io.Writer;
import java.util.*;

/**
 * List all Commands
 *
 * @author Jared Erickson
 */
public class ListCommand implements Command<Options> {

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
    public Options getOptions() {
        return new Options();
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
    public void execute(Options options, Reader reader, Writer writer) throws Exception {
        // Find all of the registered Commands
        final ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        // Collect their names
        List<String> names = new ArrayList<String>();
        for (Iterator<Command> it = commandServiceLoader.iterator(); it.hasNext(); ) {
            Command cmd = it.next();
            names.add(cmd.getName());
        }
        // Sort the names
        Collections.sort(names);
        // Write the names
        String NEW_LINE = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        for (Iterator<String> it = names.iterator(); it.hasNext(); ) {
            String name = it.next();
            builder.append(name);
            if (it.hasNext()) {
                builder.append(NEW_LINE);
            }
        }
        writer.write(builder.toString());
    }
}

package org.geometrycommands;

import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * List all Commands
 * @author Jared Erickson
 */
public class ListCommand implements Command<Options> {

    /**
     * Get the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "list";
    }

    /**
     * Get a new Options
     * @return A new Options
     */
    @Override
    public Options getOptions() {
        return new Options();
    }

    /**
     * Get a List of all Geometry Commands
     * @param options The Options
     * @param reader The java.io.Reader
     * @param writer The java.io.Writer 
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(Options options, Reader reader, Writer writer) throws Exception {
        final ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        String NEW_LINE = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        for (Iterator<Command> it = commandServiceLoader.iterator(); it.hasNext();) {
            Command cmd = it.next();
            builder.append(cmd.getName());
            if (it.hasNext()) {
                builder.append(NEW_LINE);
            }
        }
        writer.write(builder.toString());
    }
}

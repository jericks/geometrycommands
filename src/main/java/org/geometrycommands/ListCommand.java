package org.geometrycommands;

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
     * @throws Exception if an error occurs
     */
    @Override
    public void execute(Options options) throws Exception {
        final ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        for (Command cmd : commandServiceLoader) {
            System.out.println(cmd.getName());
        }
    }
}

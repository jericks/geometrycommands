package org.geometrycommands;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ServiceLoader;
import org.kohsuke.args4j.CmdLineParser;

/**
 * The command line application
 * @author Jared Erickson
 */
public class App {

    /**
     * Run the command line application
     * @param args The arguments from the command line
     */
    public static void main(String[] args) {

        // The usage 
        final String usage = "Usage: geom <command> <args>";
        
        // Check for an argument (the command name is required)
        if (args.length == 0) {
            System.err.println("Please enter a geometry command!");
            System.err.println(usage);
            System.exit(-1);
        }

        // Get the command name
        String name = args[0];

        // Lookup the Command by name
        Command command = null;
        final ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        for (Command cmd : commandServiceLoader) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                command = cmd;
                break;
            }
        }

        // If we couldn't find the Command error out
        if (command == null) {
            System.err.println("Unknown geometry command: '" + name + "'!");
            System.err.println(usage);
            System.exit(-1);
        }

        // Get the empty command line POJO
        Options options = command.getOptions();

        // Fill the command line POJO using args4j's CmdLineParser
        CmdLineParser cmdLineParser = new CmdLineParser(options);
        try {
            // Parse the arguments
            cmdLineParser.parseArgument(args);
            // Print help for the command
            if (options.isHelp()) {
                cmdLineParser.printUsage(System.out);
            } else {
                // If there are no errors, execute the command
                Reader reader = new InputStreamReader(System.in);
                Writer writer = new OutputStreamWriter(System.out);
                command.execute(options, reader, writer);
                // Add a new line character and flush
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
        } catch (Exception e) {
            // Print help for the command (if required options are not present)
            if (options.isHelp()) {
                cmdLineParser.printUsage(System.out);
            } else {
                // Oops, display the error messages to the user
                System.err.println(e.getMessage());
                System.err.println("Usage: geom <command> <args>");
                cmdLineParser.printUsage(System.err);
            }

        }
    }
}

package org.geometrycommands;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ServiceLoader;

/**
 * The command line application
 *
 * @author Jared Erickson
 */
public class App {

    /**
     * Run the command line application
     *
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
                System.out.println("geom " + command.getName() + ": " + command.getDescription());
                cmdLineParser.printUsage(System.out);
            }
            // Print the arguments on one line (for bash completion)
            else if (options.isArgs()) {
                System.out.println(getArgStringLine(cmdLineParser.printExample(ExampleMode.ALL)));
            }
            else {
                // If there are no errors, execute the command
                Reader reader = new InputStreamReader(System.in);
                Writer writer = new StringWriter();
                command.execute(options, reader, writer);
                writer.flush();
                String output = ((StringWriter) writer).getBuffer().toString();
                if (!output.isEmpty()) {
                    System.out.println(output);
                }
            }
        } catch (Exception e) {
            // Print help for the command (if required options are not present)
            if (options.isHelp()) {
                System.out.println("geom " + command.getName() + ": " + command.getDescription());
                cmdLineParser.printUsage(System.out);
            }
            // Print the arguments on one line (for bash completion)
            else if (options.isArgs()) {
                System.out.println(getArgStringLine(cmdLineParser.printExample(ExampleMode.ALL)));
            }
            else {
                // Oops, display the error messages to the user
                System.err.println(e.getMessage());
                System.err.println("Usage: geom <command> <args>");
                cmdLineParser.printUsage(System.err);
            }

        }
    }

    private static String getArgStringLine(String argStr) {
        argStr = argStr.replaceAll("\\(","").replaceAll("\\)","");
        StringBuilder b = new StringBuilder();
        for(String str : argStr.split(" ")) {
            if (str.startsWith("-")) {
                b.append(str).append(" ");
            }
        }
        return b.toString().trim();
    }
}

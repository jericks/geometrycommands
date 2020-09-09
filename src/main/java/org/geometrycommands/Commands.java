package org.geometrycommands;

import org.kohsuke.args4j.CmdLineParser;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.*;
import java.util.List;

public class Commands {

    public static Optional<Command> find(String name) {
        final ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        for (Command cmd : commandServiceLoader) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return Optional.of(cmd);
            }
        }
        return Optional.empty();
    }

    public static List<Command> getAll() {
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
        return commands;
    }

    public static class CommandException extends Exception {
    }
    
    public static void execute(String[] args, Reader inputReader, Writer outputWriter, Writer errorWriter) throws CommandException {
        
        // The usage 
        final String usage = "Usage: geom <command> <args>";

        // Check for an argument (the command name is required)
        if (args.length == 0) {
            println(errorWriter,"Please enter a geometry command!");
            println(errorWriter, usage);
            flushSilently(errorWriter);
            throw new CommandException();
        }

        // Get the command name
        String name = args[0];

        // Lookup the Command by name
        Optional<Command> optionalCommand = find(name);

        // If we couldn't find the Command error out
        if (!optionalCommand.isPresent()) {
            println(errorWriter,"Unknown geometry command: '" + name + "'!");
            println(errorWriter,usage);
            flushSilently(errorWriter);
            throw new CommandException();
        }
        Command command = optionalCommand.get();
            
        // Get the empty command line POJO
        Options options = command.getOptions();

        // Fill the command line POJO using args4j's CmdLineParser
        CmdLineParser cmdLineParser = new CmdLineParser(options);
        try {
            // Parse the arguments
            cmdLineParser.parseArgument(args);
            // Print help for the command
            if (options.isHelp()) {
                println(outputWriter,"geom " + command.getName() + ": " + command.getDescription());
                cmdLineParser.printUsage(outputWriter, null);
            }
            else if (options.isWebHelp()) {
                URI uri = new URI("http://jericks.github.io/geometrycommands/commands/" + name + ".html");
                Desktop.getDesktop().browse(uri);
            }
            else {
                // If there are no errors, execute the command
                Writer writer = new StringWriter();
                command.execute(options, inputReader, writer);
                writer.flush();
                outputWriter.write(writer.toString());
                outputWriter.flush();
            }
        } catch (Exception e) {
            // Print help for the command (if required options are not present)
            if (options.isHelp()) {
                println(outputWriter,"geom " + command.getName() + ": " + command.getDescription());
                cmdLineParser.printUsage(outputWriter, null);
            }
            // Open help in a browser
            else if (options.isWebHelp()) {
                try {
                    URI uri = new URI("http://jericks.github.io/geometrycommands/commands/" + name + ".html");
                    Desktop.getDesktop().browse(uri);
                } catch(Exception ex) {
                    println(errorWriter,"Unable to open help in web browser!");
                }
            }
            else {
                // Oops, display the error messages to the user
                println(errorWriter,e.getMessage());
                println(errorWriter,"Usage: geom <command> <args>");
                cmdLineParser.printUsage(errorWriter, null);
            }

        }
    }
    
    private static void println(Writer writer, String message) {
        try {
            writer.write(message);
            writer.write("\n");
        } catch (IOException e) {
        }
    }

    private static void flushSilently(Writer writer) {
        try {
            writer.flush();
        }
        catch (IOException e) {
        }
    }
}

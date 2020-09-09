package org.geometrycommands;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.geometrycommands.PipeCommand.PipeOptions;
import org.kohsuke.args4j.Option;

public class PipeCommand implements Command<PipeOptions> {

    @Override
    public String getName() {
        return "pipe";
    }

    @Override
    public String getDescription() {
        return "Combine multiple commands together with a pipe.";
    }

    @Override
    public PipeOptions getOptions() {
        return new PipeOptions();
    }

    @Override
    public void execute(PipeOptions options, Reader reader, Writer writer) throws Exception {

        String[] commands = options.getCommands().split("\\|");

        Reader subReader = reader;
        StringWriter subWriter = new StringWriter();
        StringWriter errorWriter = new StringWriter();
        String output = "";

        for(String command : commands) {
            String[] subArgs = translateCommandline(command.trim());
            Commands.execute(subArgs, subReader, subWriter, errorWriter);
            String errorMessage = errorWriter.toString().trim();
            if (!errorMessage.isEmpty()) {
                throw new Exception(errorMessage);
            }
            output = subWriter.toString();
            subReader = new StringReader(output);
            subWriter = new StringWriter();
            errorWriter = new StringWriter();
        }
        writer.write(output);
        writer.write("\n");
    }

    public static void main(String[] args) throws Exception {
        PipeCommand cmd = new PipeCommand();
        PipeOptions options = new PipeOptions();
        options.setCommands("buffer -d 5 -g \"POINT(1 1)\" | centroid");//

        StringReader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        cmd.execute(options, reader, writer);
        System.out.println(writer.toString());
    }

    /**
     * From Ant
     */
    private String[] translateCommandline(String toProcess) {
        if (toProcess == null || toProcess.isEmpty()) {
            return new String[0];
        }

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"' ", true);
        final ArrayList<String> result = new ArrayList<>();
        final StringBuilder current = new StringBuilder();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
                case inQuote:
                    if ("'".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                case inDoubleQuote:
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                default:
                    if ("'".equals(nextTok)) {
                        state = inQuote;
                    } else if ("\"".equals(nextTok)) {
                        state = inDoubleQuote;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() > 0) {
                            result.add(current.toString());
                            current.setLength(0);
                        }
                    } else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                    break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() > 0) {
            result.add(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new IllegalArgumentException("unbalanced quotes in " + toProcess);
        }
        return result.toArray(new String[result.size()]);
    }

    public static class PipeOptions extends Options {

        @Option(name = "-c", aliases = "--commands", usage="Commands separate by pipe", required = true)
        private String commands;

        public String getCommands() {
            return commands;
        }

        public void setCommands(String commands) {
            this.commands = commands;
        }
    }

}

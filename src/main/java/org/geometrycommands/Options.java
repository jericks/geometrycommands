package org.geometrycommands;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * The base class Options for all Commands
 * @author Jared Erickson
 */
public class Options {

    /**
     * The Command name
     */
    @Argument(index = 0, required = true)
    private String name;

    /**
     * The flag to print the help message
     */
    @Option(name = "--help", usage = "Print help message", required = false)
    private boolean help;

    /**
     * The flag to print the argument list
     */
    @Option(name = "--args", usage = "Print argument list", required = false)
    private boolean args;

    /**
     * Get the Command name
     * @return The Command name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Command name
     * @param name The Command name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the flag to print the help message
     * @return The flag to print the help message
     */
    public boolean isHelp() {
        return help;
    }

    /**
     * Set the flag to print the help message
     * @param help The flag to print the help message
     */
    public void setHelp(boolean help) {
        this.help = help;
    }

    public boolean isArgs() {
        return args;
    }

    public void setArgs(boolean args) {
        this.args = args;
    }
}

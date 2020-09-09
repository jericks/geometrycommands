package org.geometrycommands;

import java.io.*;

/**
 * The command line application
 * @author Jared Erickson
 */
public class App {

    /**
     * Run the command line application
     *
     * @param args The arguments from the command line
     */
    public static void main(String[] args) {
        try {
            Commands.execute(
                    args,
                    new InputStreamReader(System.in),
                    new OutputStreamWriter(System.out),
                    new OutputStreamWriter(System.err)
            );
        } catch (Commands.CommandException e) {
            System.exit(-1);
        }
    }
}

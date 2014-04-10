package org.geometrycommands;

import java.io.*;
import java.util.List;

/**
 * The Base Test class
 * @author Jared Erickson
 */
public abstract class BaseTest {

    public String runApp(String[] args, String input) throws Exception {
        // Save normal System.in and System.out
        InputStream sysin = System.in;
        PrintStream sysout = System.out;
        // Replace System.in with input text
        if (input == null) {
            input = "";
        }
        System.setIn(new ByteArrayInputStream(input.getBytes("UTF-8")));
        // Replace System.out with OutputStream we can capture
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // Run the app with the List of arguments
        App.main(args);
        // Replace normal System.in and System.out
        System.setIn(sysin);
        System.setOut(sysout);
        // Return the captured output
        return out.toString("UTF-8").trim();
    }

}

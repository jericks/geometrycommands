package org.geometrycommands;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Base Test class
 * @author Jared Erickson
 */
public abstract class BaseTest {

    public static final String NEW_LINE = System.getProperty("line.separator");

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

    public Map<String,String> runAppWithOutAndErr(String[] args, String input) throws Exception {
        // Save normal System.in and System.out
        InputStream sysin = System.in;
        PrintStream syserr = System.err;
        PrintStream sysout = System.out;
        // Replace System.in with input text
        if (input == null) {
            input = "";
        }
        System.setIn(new ByteArrayInputStream(input.getBytes("UTF-8")));
        // Replace System.out with OutputStream we can capture
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // Replace System.err with OutputStream we can capture
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        System.setErr(new PrintStream(err));
        // Run the app with the List of arguments
        App.main(args);
        // Replace normal System.in and System.out
        System.setIn(sysin);
        System.setOut(sysout);
        System.setErr(syserr);
        // Return the captured output
        Map<String,String> outputs =new HashMap<String, String>();
        outputs.put("out", out.toString("UTF-8").trim());
        outputs.put("err", err.toString("UTF-8").trim());
        return outputs;
    }
}

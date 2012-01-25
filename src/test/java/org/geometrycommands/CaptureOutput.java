package org.geometrycommands;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A Utility class that captures standard output as a string.
 * @author Jared Erickson
 */
public class CaptureOutput {

    /**
     * The original standard output stream
     */
    private PrintStream originalOutputStream;

    /**
     * The OutputStream used to capture standard output stream
     */
    private OutputStream out;

    /**
     * The original standard error stream
     */
    private PrintStream originalErrorOutputStream;

    /**
     * The OutputStream used to capture standard error stream
     */
    private OutputStream err;

    /**
     * Start capturing standard output
     */
    public void start() {
        originalOutputStream = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        originalErrorOutputStream = System.err;
        err = new ByteArrayOutputStream();
        System.setErr(new PrintStream(err));
    }

    /**
     * Create a new CaptureOutput and start capturing 
     * @return The new CaptureOutput
     */
    public static CaptureOutput createAndStart() {
        CaptureOutput capture = new CaptureOutput();
        capture.start();
        return capture;
    }

    /**
     * Stop capturing standard output.
     * @return A Map of Strings with the standard output value under the out key
     * and the standard error value under the err key
     */
    public Map<String, String> stop() {
        String str = out.toString().trim();
        System.setOut(originalOutputStream);

        String errStr = err.toString().trim();
        System.setErr(originalErrorOutputStream);

        Map<String, String> map = new HashMap<String, String>();
        map.put("out", str);
        map.put("err", errStr);

        return map;
    }
}

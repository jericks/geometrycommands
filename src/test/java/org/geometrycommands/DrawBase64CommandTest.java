package org.geometrycommands;

import org.geometrycommands.DrawBase64Command.DrawOptions;
import org.junit.Test;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The DrawBase64Command UnitTest
 *
 * @author Jared Erickson
 */
public class DrawBase64CommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {
        // PNG no prefix
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        DrawOptions options = new DrawOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DrawBase64Command command = new DrawBase64Command();
        command.execute(options, reader, writer);
        String result = writer.toString();
        assertTrue(result.length() > 0);
        assertFalse(result.startsWith("data:image/jpeg;base64"));

        // JPEG with prefix
        options = new DrawOptions();
        options.setGeometry(inputGeometry);
        options.setType("jpeg");
        options.setIncludingPrefix(true);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new DrawBase64Command();
        command.execute(options, reader, writer);
        result = writer.toString().trim();
        assertTrue(result.length() > 0);
        assertTrue(result.startsWith("data:image/jpeg;base64"));
    }

    @Test
    public void run() throws Exception {
        // PNG, no prefix, geometry from options
        String result = runApp(new String[]{
                "drawbase64",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null).trim();
        assertTrue(result.length() > 0);
        assertFalse(result.startsWith("data:image/jpeg;base64"));

        // JPEG with prefix, geometry from input stream
        result = runApp(new String[]{
                "drawbase64",
                "-y", "jpeg",
                "-p",
                "-w", "300",
                "-h", "250",
                "-b", "0,0,0",
                "-s", "255,255,255",
                "-t", "0.75",
                "-l", "255,255,0",
                "-o", "0.55",
                "-m", "square",
                "-z", "12",
                "-e", "0,0,10,10"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))").trim();
        assertTrue(result.length() > 0);
        assertTrue(result.startsWith("data:image/jpeg;base64"));
    }
}

package org.geometrycommands;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.DrawCommand.DrawOptions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The DrawCommand UnitTest
 * @author Jared Erickson
 */
public class DrawCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        File file = File.createTempFile("image", ".png");
        DrawOptions options = new DrawOptions();
        options.setGeometry(inputGeometry);
        options.setFile(file);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        DrawCommand command = new DrawCommand();
        command.execute(options, reader, writer);
        assertTrue(file.exists());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        File file = File.createTempFile("image", ".png");
        String result = runApp(new String[]{
                "draw",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-f", file.getAbsolutePath()
        }, null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // Geometry from input stream
        file = File.createTempFile("image", ".png");
        result = runApp(new String[]{
                "draw",
                "-w", "300",
                "-h", "250",
                "-f", file.getAbsolutePath(),
                "-b", "0,0,0",
                "-s", "255,255,255",
                "-t", "0.75",
                "-l", "255,255,0",
                "-o", "0.55",
                "-m", "square",
                "-z", "12",
                "-e", "0,0,10,10"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}

package org.geometrycommands;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.DrawCommand.DrawOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        options.setWidth(400);
        options.setHeight(400);
        options.setFile(file);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        DrawCommand command = new DrawCommand();
        command.execute(options, reader, writer);
        assertTrue(file.exists());
    }

    @Test
    public void executeDrawingCoordinates() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        File file = File.createTempFile("image", ".png");
        DrawOptions options = new DrawOptions();
        options.setGeometry(inputGeometry);
        options.setBounds("0,0,10,10");
        options.setBackgroundColor("255,0,255");
        options.setDrawingCoordinates(true);
        options.setMarkerSize(12);
        options.setFillColor("0,0,255");
        options.setFillOpacity(0.5f);
        options.setStrokeColor("255,255,0");
        options.setStrokeOpacity(0.95f);
        options.setStrokeWidth(1.2f);
        options.setShape("square");
        options.setBackgroundImage(" ");
        options.setFile(file);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        DrawCommand command = new DrawCommand();
        command.execute(options, reader, writer);
        assertTrue(file.exists());
    }

    @Test
    public void executeWithBackgroundImage() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        File file = File.createTempFile("image", ".png");
        DrawOptions options = new DrawOptions();
        options.setGeometry(inputGeometry);
        options.setWidth(400);
        options.setHeight(400);
        options.setFile(file);
        File backgroundImageFile = new File(getClass().getClassLoader().getResource("grid.png").toURI());
        options.setBackgroundImage(backgroundImageFile.getAbsolutePath());

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
        runApp(new String[]{
                "draw",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-f", file.getAbsolutePath()
        }, null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // Geometry from input stream
        file = File.createTempFile("image", ".png");
        runApp(new String[]{
                "draw",
                "-w", "300",
                "-h", "250",
                "-f", file.getAbsolutePath(),
                "-b", "0,0,0",
                "-s", "255,255,255",
                "-t", "0.75",
                "-l", "255,255,0",
                "-o", "0.55",
                "-m", "star",
                "-z", "12",
                "-e", "0,0,10,10"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file = File.createTempFile("image", ".png");
        runApp(new String[]{
                "draw",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-f", file.getAbsolutePath(),
                "-e", "0,0,10,0",
                "-m", "cross"
        }, null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file = File.createTempFile("image", ".png");
        runApp(new String[]{
                "draw",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-f", file.getAbsolutePath(),
                "-e", "0,0,0,10",
                "-m", "triangle"
        }, null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file = File.createTempFile("image", ".png");
        runApp(new String[]{
                "draw",
                "-g", "POINT (5 5)",
                "-f", file.getAbsolutePath(),
                "-e", "5,5,5,5",
                "-m", "square"
        }, null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file = File.createTempFile("image", ".png");
        runApp(new String[]{
                "draw",
                "-g", "LINESTRING (0 0, 10 20)",
                "-f", file.getAbsolutePath(),
                "-c",
                "-e", "0,0,10", // Bad bounds
                "-m", "x"
        }, null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}

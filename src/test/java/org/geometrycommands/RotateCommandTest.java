package org.geometrycommands;

import org.geometrycommands.RotateCommand.RotateOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The RotateCommand UnitTest
 * @author Jared Erickson
 */
public class RotateCommandTest extends BaseTest {

    @Test 
    public void execute() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        RotateOptions options = new RotateOptions();
        options.setGeometry(inputGeometry);
        options.setTheta(Math.toRadians(45));
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        RotateCommand command = new RotateCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", writer.getBuffer().toString());
    }

    @Test
    public void executeThetaXY() throws Exception {
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        RotateOptions options = new RotateOptions();
        options.setGeometry(inputGeometry);
        options.setTheta(Math.toRadians(45));
        options.setX(1);
        options.setY(2);
        options.setSinTheta(Double.NaN);
        options.setCosTheta(Double.NaN);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        RotateCommand command = new RotateCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((1.7071067811865475 -0.1213203435596426, -5.363961030678928 6.949747468305833, " +
                "1.7071067811865483 14.020815280171309, 8.778174593052023 6.949747468305832, " +
                "1.7071067811865475 -0.1213203435596426))", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "rotate",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-t", String.valueOf(Math.toRadians(45))
        }, null);
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "rotate",
                "-t", String.valueOf(Math.toRadians(45))
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", result);

        // theta
        result = runApp(new String[]{
                "rotate",
                "-t", String.valueOf(Math.toRadians(45)),
                "-x", "2"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", result);

        result = runApp(new String[]{
                "rotate",
                "-t", String.valueOf(Math.toRadians(45)),
                "-y", "2"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -7.071067811865475 7.0710678118654755, "
                + "0.0000000000000009 14.142135623730951, 7.0710678118654755 "
                + "7.071067811865475, 0 0))", result);

        // theta, x, y
        result = runApp(new String[]{
                "rotate",
                "-t", String.valueOf(Math.toRadians(45)),
                "-x", "4",
                "-y", "3"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((3.292893218813452 -1.9497474683058327, -3.7781745930520225 5.121320343559643, " +
                "3.292893218813453 12.192388155425117, 10.363961030678928 5.121320343559642, " +
                "3.292893218813452 -1.9497474683058327))", result);

        // sinTheta, cosTheta, x, y
        result = runApp(new String[]{
                "rotate",
                "-s", "4",
                "-c", "3",
                "-x", "4",
                "-y", "3"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((4 -22, -36 8, -6 48, 34 18, 4 -22))", result);

        // sinTheta, cosTheta
        result = runApp(new String[]{
                "rotate",
                "-s", "4",
                "-c", "3"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -40 30, -10 70, 30 40, 0 0))", result);

        result = runApp(new String[]{
                "rotate",
                "-s", "4",
                "-c", "3",
                "-x", "4"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -40 30, -10 70, 30 40, 0 0))", result);

        result = runApp(new String[]{
                "rotate",
                "-s", "4",
                "-c", "3",
                "-y", "4"
        }, "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))");
        assertEquals("POLYGON ((0 0, -40 30, -10 70, 30 40, 0 0))", result);
    }

    @Test
    public void runWithInvalidAlgorithm() throws Exception {
        Map<String, String> result = runAppWithOutAndErr(new String[]{
                "rotate",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-s", "10",
                "-x", "2"
        }, null);
        assertEquals("Illegal combination of arguments (theta | theta,x,y | sinTheta, cosTheta, x, y | sinTheta, cosTheta" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help               : Print help message (default: false)" + NEW_LINE +
                " -c (--cosine) N      : The cosine of the rotation angle (default: NaN)" + NEW_LINE +
                " -g (--geometry) VAL  : The input geometry (default: POLYGON ((0 0, 0 10, 10" + NEW_LINE +
                "                        10, 10 0, 0 0)))" + NEW_LINE +
                " -s (--sine) N        : The sine of the rotation angle (default: 10.0)" + NEW_LINE +
                " -t (--theta) N       : The rotation angle, in radians (default: NaN)" + NEW_LINE +
                " -x (--xCoordinate) N : The x-ordinate of the rotation point (default: 2.0)" + NEW_LINE +
                " -y (--yCoordinate) N : The y-ordinate of the rotation point (default: NaN)", result.get("err"));

        result = runAppWithOutAndErr(new String[]{
                "rotate",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))",
                "-c", "10",
                "-x", "2"
        }, null);
        assertEquals("Illegal combination of arguments (theta | theta,x,y | sinTheta, cosTheta, x, y | sinTheta, cosTheta" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help               : Print help message (default: false)" + NEW_LINE +
                " -c (--cosine) N      : The cosine of the rotation angle (default: 10.0)" + NEW_LINE +
                " -g (--geometry) VAL  : The input geometry (default: POLYGON ((0 0, 0 10, 10" + NEW_LINE +
                "                        10, 10 0, 0 0)))" + NEW_LINE +
                " -s (--sine) N        : The sine of the rotation angle (default: NaN)" + NEW_LINE +
                " -t (--theta) N       : The rotation angle, in radians (default: NaN)" + NEW_LINE +
                " -x (--xCoordinate) N : The x-ordinate of the rotation point (default: 2.0)" + NEW_LINE +
                " -y (--yCoordinate) N : The y-ordinate of the rotation point (default: NaN)", result.get("err"));
    }
}

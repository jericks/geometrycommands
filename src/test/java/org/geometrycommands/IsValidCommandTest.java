package org.geometrycommands;

import org.geometrycommands.IsValidCommand.IsValidOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The IsValidCommand UnitTest
 * @author Jared Erickson
 */
public class IsValidCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        IsValidOptions options = new IsValidOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsValidCommand command = new IsValidCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "POLYGON ((17.06298828125 49.7998046875, "
                + "14.25048828125 44.04296875, 18.24951171875 44.04296875, "
                + "13.45947265625 48.1298828125, 17.06298828125 "
                + "49.7998046875))";
        options = new IsValidOptions();
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
        
        // message
        options.setGeometry(inputGeometry);
        options.setType("msg");
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("Self-intersection", writer.getBuffer().toString());

        options.setGeometry(inputGeometry);
        options.setType("message");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("Self-intersection", writer.getBuffer().toString());

        // location
        options.setGeometry(inputGeometry);
        options.setType("loc");
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("POINT (15.42700884375309 46.45115927637351)", writer.getBuffer().toString());

        options.setGeometry(inputGeometry);
        options.setType("location");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("POINT (15.42700884375309 46.45115927637351)", writer.getBuffer().toString());

        // validity
        options.setGeometry(inputGeometry);
        options.setType("val");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());

        options.setGeometry(inputGeometry);
        options.setType("validity");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "isvalid",
                "-g", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
        }, null);
        assertTrue(Boolean.parseBoolean(result));

        // Geometry from input stream
        result = runApp(new String[]{
                "isvalid"
        }, "POLYGON ((17.06298828125 49.7998046875, "
                + "14.25048828125 44.04296875, 18.24951171875 44.04296875, "
                + "13.45947265625 48.1298828125, 17.06298828125 "
                + "49.7998046875))");
        assertFalse(Boolean.parseBoolean(result));

        // message
        result = runApp(new String[]{
                "isvalid",
                "-t", "msg"
        }, "POLYGON ((17.06298828125 49.7998046875, "
                + "14.25048828125 44.04296875, 18.24951171875 44.04296875, "
                + "13.45947265625 48.1298828125, 17.06298828125 "
                + "49.7998046875))");
        assertEquals("Self-intersection", result);

        // location
        result = runApp(new String[]{
                "isvalid",
                "-t", "loc"
        }, "POLYGON ((17.06298828125 49.7998046875, "
                + "14.25048828125 44.04296875, 18.24951171875 44.04296875, "
                + "13.45947265625 48.1298828125, 17.06298828125 "
                + "49.7998046875))");
        assertEquals("POINT (15.42700884375309 46.45115927637351)", result);
    }

    @Test
    public void runWithWrongType() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "isvalid",
                "-g", "POINT (1 1)",
                "-t", "ASDF"
        }, null);
        assertEquals("Unknown type!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: POINT (1 1))" + NEW_LINE +
                " -t (--type) VAL     : The flag to show the validation error message, the error" + NEW_LINE +
                "                       location, or validity (msg, loc, or val) (default: ASDF)", result.get("err"));
    }
}

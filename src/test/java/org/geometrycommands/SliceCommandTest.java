package org.geometrycommands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.SliceCommand.SliceOptions;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * The SliceGeometryCommand Unit Test
 * @author Jared Erickson
 */
public class SliceCommandTest extends BaseTest {

   @Test
   public void execute() throws Exception {
       String input = "MULTIPOINT ((1 1), (2 2), (3 3), (4 4), (5 5))";
       SliceCommand cmd = new SliceCommand();
       SliceOptions options = new SliceOptions();

       // 1 - 3
       options.setGeometry(input);
       options.setStart(1);
       options.setEnd(3);
       Reader reader = new StringReader("");
       StringWriter writer = new StringWriter();
       cmd.execute(options, reader, writer);
       assertEquals("MULTIPOINT ((2 2), (3 3))", writer.toString());

       // 2
       options.setGeometry(input);
       options.setStart(2);
       options.setEnd(null);
       writer = new StringWriter();
       cmd.execute(options, reader, writer);
       assertEquals("MULTIPOINT ((3 3), (4 4), (5 5))", writer.toString());

       // -3
       options.setGeometry(input);
       options.setStart(-3);
       options.setEnd(null);
       writer = new StringWriter();
       cmd.execute(options, reader, writer);
       assertEquals("MULTIPOINT ((3 3), (4 4), (5 5))", writer.toString());

       // -4, -2
       options.setGeometry(input);
       options.setStart(-4);
       options.setEnd(-2);
       writer = new StringWriter();
       cmd.execute(options, reader, writer);
       assertEquals("MULTIPOINT ((2 2), (3 3))", writer.toString());

       // 0, -2
       options.setGeometry(input);
       options.setStart(0);
       options.setEnd(-2);
       writer = new StringWriter();
       cmd.execute(options, reader, writer);
       assertEquals("MULTIPOINT ((1 1), (2 2), (3 3))", writer.toString());
   }

    @Test
    public void run() throws Exception {

        String input = "MULTIPOINT ((1 1), (2 2), (3 3), (4 4), (5 5))";

        // Geometry from options
        String result = runApp(new String[]{
                "slice",
                "-g", input,
                "-s", "1",
                "-e", "3"
        }, null);
        assertEquals("MULTIPOINT ((2 2), (3 3))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "slice",
                "-s", "0",
                "-e", "-2"
        }, input);
        assertEquals("MULTIPOINT ((1 1), (2 2), (3 3))", result);
    }

    @Test
    public void runWithStartGreaterThanLength() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "slice",
                "-g", "MULTIPOINT ((1 1), (2 2), (3 3))",
                "-s", "4",
                "-e", "2"
        }, null);
        assertEquals("Start index can not be more than the number of items!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -e (--end) N        : The end index number (default: 2)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: MULTIPOINT ((1 1), (2 2)," + NEW_LINE +
                "                       (3 3)))" + NEW_LINE +
                " -s (--start) N      : The start index number", result.get("err"));
    }

    @Test
    public void runWithEndGreaterThanLength() throws Exception {
        Map<String,String> result = runAppWithOutAndErr(new String[]{
                "slice",
                "-g", "MULTIPOINT ((1 1), (2 2), (3 3))",
                "-s", "1",
                "-e", "5"
        }, null);
        assertEquals("End index can not be more than the number of items!" + NEW_LINE +
                "Usage: geom <command> <args>" + NEW_LINE +
                " --help              : Print help message (default: false)" + NEW_LINE +
                " --web-help          : Open help in a web browser (default: false)" + NEW_LINE +
                " -e (--end) N        : The end index number (default: 5)" + NEW_LINE +
                " -g (--geometry) VAL : The input geometry (default: MULTIPOINT ((1 1), (2 2)," + NEW_LINE +
                "                       (3 3)))" + NEW_LINE +
                " -s (--start) N      : The start index number", result.get("err"));
    }
}

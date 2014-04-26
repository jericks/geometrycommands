package org.geometrycommands;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.SliceCommand.SliceOptions;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

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

}

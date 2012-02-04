package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The RectangleCommand UnitTest
 * @author Jared Erickson
 */
public class RectangleCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        ShapeFactoryOptions options = new ShapeFactoryOptions();
        options.setGeometry(inputGeometry);
        options.setWidth(50);
        options.setHeight(40);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        RectangleCommand command = new RectangleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((100 100, 125 100, 150 100, 150 120, 150 140, "
                + "125 140, 100 140, 100 120, 100 100))",
                writer.getBuffer().toString());
    }
}

package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SquircleCommand UnitTest
 * @author Jared Erickson
 */
public class SquircleCommandTest {

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

        SquircleCommand command = new SquircleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((125 140, 141.8179283050743 136.8179283050743, "
                + "145 120, 141.8179283050743 103.18207169492571, "
                + "125 100, 108.18207169492571 103.18207169492571, 105 120, "
                + "108.18207169492571 136.8179283050743, 125 140))",
                writer.getBuffer().toString());
    }
}

package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The IntersectionCommand UnitTest
 * @author Jared Erickson
 */
public class IntersectionCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((5 5, 5 20, 20 20, 20 5, 5 5))";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IntersectionCommand command = new IntersectionCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((5 10, 10 10, 10 5, 5 5, 5 10))",
                writer.getBuffer().toString());
    }
}
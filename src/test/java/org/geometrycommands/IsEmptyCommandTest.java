package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The IsEmptyCommand UnitTest
 * @author Jared Erickson
 */
public class IsEmptyCommandTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POINT EMPTY";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsEmptyCommand command = new IsEmptyCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "POINT (15 15)";
        options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new IsEmptyCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }
}

package org.geometrycommands;

import org.geometrycommands.IsWithinDistanceCommand.IsWithinDistanceOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The IsWithinDistanceCommand UnitTest
 * @author Jared Erickson
 */
public class IsWithinDistanceCommandTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POINT (1 1)";
        String otherGeometry = "POINT (20 23)";
        IsWithinDistanceOptions options = new IsWithinDistanceOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setDistance(30);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        IsWithinDistanceCommand command = new IsWithinDistanceCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        options.setDistance(10);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new IsWithinDistanceCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }
}

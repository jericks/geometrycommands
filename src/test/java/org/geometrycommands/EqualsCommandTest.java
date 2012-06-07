package org.geometrycommands;

import org.geometrycommands.EqualsCommand.EqualsOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The EqualsCommand UnitTest
 * @author Jared Erickson
 */
public class EqualsCommandTest {

    @Test
    public void execute() throws Exception {

        // true
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        EqualsOptions options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        EqualsCommand command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());

        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((1 1, 1 10, 10 10, 10 1, 1 1))";
        options = new EqualsOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();

        command = new EqualsCommand();
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }
}
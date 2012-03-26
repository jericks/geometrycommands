package org.geometrycommands;

import org.geometrycommands.PrecisionReducerCommand.PrecisionReducerOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The PrecisionReducerCommand UnitTest
 * @author Jared Erickson
 */
public class PrecisionReducerCommandTest {

    @Test
    public void execute() throws Exception {

        PrecisionReducerCommand command = new PrecisionReducerCommand();
        
        // Floating
        String inputGeometry = "POINT (5.19775390625 51.07421875)";
        PrecisionReducerOptions options = new PrecisionReducerOptions();
        options.setGeometry(inputGeometry);
        options.setPrecisionModelType("floating");

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (5.19775390625 51.07421875)", writer.getBuffer().toString());
        
        // Fixed
        inputGeometry = "POINT (5.19775390625 51.07421875)";
        options = new PrecisionReducerOptions();
        options.setGeometry(inputGeometry);
        options.setPrecisionModelType("fixed");
        options.setPrecisionModelScale(10);

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (5.2 51.1)", writer.getBuffer().toString());
        
        // Floating Single
        inputGeometry = "POINT (5.19775390625 51.07421875)";
        options = new PrecisionReducerOptions();
        options.setGeometry(inputGeometry);
        options.setPrecisionModelType("FLOATING_SINGLE");

        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (5.19775390625 51.07421875)", writer.getBuffer().toString());
    }
}
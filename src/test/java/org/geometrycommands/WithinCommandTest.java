package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The WithinCommand UnitTest
 * @author Jared Erickson
 */
public class WithinCommandTest {

    @Test 
    public void execute() throws Exception {
        
        // true
        String inputGeometry = "POINT (5 5)";
        String otherGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        OtherGeometryOptions options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        WithinCommand command = new WithinCommand();
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
        
        // false
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POINT (15 15)";
        options = new OtherGeometryOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("false", writer.getBuffer().toString());
    }
}

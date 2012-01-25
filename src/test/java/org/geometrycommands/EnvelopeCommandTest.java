package org.geometrycommands;

import org.geometrycommands.EnvelopeCommand.EnvelopeOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The EnvelopeCommand UnitTest
 * @author Jared Erickson
 */
public class EnvelopeCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        EnvelopeOptions options = new EnvelopeOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        EnvelopeCommand command = new EnvelopeCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", writer.getBuffer().toString());
    }
    
    @Test 
    public void executeWithExpandBy() throws Exception {
        
        String inputGeometry = "POINT (5 5)";
        EnvelopeOptions options = new EnvelopeOptions();
        options.setGeometry(inputGeometry);
        options.setExpandBy(5);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        EnvelopeCommand command = new EnvelopeCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", writer.getBuffer().toString());
    }
    
}
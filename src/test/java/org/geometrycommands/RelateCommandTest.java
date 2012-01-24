package org.geometrycommands;

import org.geometrycommands.RelateCommand.RelateOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The RelateCommand UnitTest
 * @author Jared Erickson
 */
public class RelateCommandTest {

    @Test 
    public void execute() throws Exception {
        
        // Get Matrix
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POINT (5 5)";
        RelateOptions options = new RelateOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        RelateCommand command = new RelateCommand();
        command.execute(options, reader, writer);
        assertEquals("0F2FF1FF2", writer.getBuffer().toString());
        
        // Is Related?
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POINT (5 5)";
        options = new RelateOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setIntersectionMatrix("0F2FF1FF2");
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("true", writer.getBuffer().toString());
    }
}

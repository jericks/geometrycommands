package org.geometrycommands;

import org.geometrycommands.PointAtAngleCommand.PointAtAngleOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The PointAtAngleCommand UnitTest
 * @author Jared Erickson
 */
public class PointAtAngleCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POINT (10 10)";
        PointAtAngleOptions options = new PointAtAngleOptions();
        options.setGeometry(inputGeometry);
        options.setAngle(90);
        options.setDistance(10);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        PointAtAngleCommand command = new PointAtAngleCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (10 20)", writer.getBuffer().toString());
        
        inputGeometry = "POINT (10 10)";
        options = new PointAtAngleOptions();
        options.setGeometry(inputGeometry);
        options.setAngle(45);
        options.setDistance(5);
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("POINT (13.535533905932738 13.535533905932738)", writer.getBuffer().toString());
    }
}

package org.geometrycommands;

import org.geometrycommands.ScaleCommand.ScaleOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ScaleCommand UnitTest
 * @author Jared Erickson
 */
public class ScaleCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        ScaleOptions options = new ScaleOptions();
        options.setGeometry(inputGeometry);
        options.setXScale(2);
        options.setYScale(5);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ScaleCommand command = new ScaleCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 0 50, 20 50, "
                + "20 0, 0 0))", writer.getBuffer().toString());
    }
}

package org.geometrycommands;

import org.geometrycommands.ShearCommand.ShearOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ShearCommand UnitTest
 * @author Jared Erickson
 */
public class ShearCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        ShearOptions options = new ShearOptions();
        options.setGeometry(inputGeometry);
        options.setX(4);
        options.setY(2);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        ShearCommand command = new ShearCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((0 0, 40 10, "
                + "50 30, 10 20, 0 0))", writer.getBuffer().toString());
    }
}

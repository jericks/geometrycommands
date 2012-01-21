package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author jericks
 */
public class CentroidCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        CentroidCommand command = new CentroidCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (5 5)", writer.getBuffer().toString());
    }
}

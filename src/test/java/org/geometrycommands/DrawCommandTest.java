package org.geometrycommands;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.DrawCommand.DrawOptions;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * The DrawCommand UnitTest
 * @author Jared Erickson
 */
public class DrawCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        File file = File.createTempFile("image", ".png");
        System.out.println(file);
        DrawOptions options = new DrawOptions();
        options.setGeometry(inputGeometry);
        options.setFile(file);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        DrawCommand command = new DrawCommand();
        command.execute(options, reader, writer);
        assertTrue(file.exists());
    }
}

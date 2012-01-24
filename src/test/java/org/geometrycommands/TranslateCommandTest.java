package org.geometrycommands;

import org.geometrycommands.TranslateCommand.TranslateOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The TranslateCommand UnitTest
 * @author Jared Erickson
 */
public class TranslateCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        TranslateOptions options = new TranslateOptions();
        options.setGeometry(inputGeometry);
        options.setX(4);
        options.setY(2);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        TranslateCommand command = new TranslateCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((4 2, 4 12, 14 12, 14 2, 4 2))", 
                writer.getBuffer().toString());
    }
}

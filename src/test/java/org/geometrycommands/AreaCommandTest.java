/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The AreaCommand UnitTest
 * @author Jared Erickson
 */
public class AreaCommandTest {
    
    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        AreaCommand command = new AreaCommand();
        command.execute(options, reader, writer);
        assertEquals("100.0", writer.getBuffer().toString());
    }
}

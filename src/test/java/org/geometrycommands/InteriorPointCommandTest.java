package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The InteriorPointCommand UnitTest
 * @author Jared Erickson
 */
public class InteriorPointCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "POLYGON ((9.89990234375 52.7880859375, "
                + "9.89990234375 49.931640625, 15.39306640625 49.580078125, "
                + "15.48095703125 43.251953125, 8.97705078125 43.251953125, "
                + "9.24072265625 40.615234375, 19.61181640625 40.5712890625, "
                + "18.38134765625 52.4365234375, 9.89990234375 52.7880859375))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        InteriorPointCommand command = new InteriorPointCommand();
        command.execute(options, reader, writer);
        assertEquals("POINT (17.205851236979164 46.6796875)", writer.getBuffer().toString());
    }
}
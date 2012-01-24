package org.geometrycommands;

import org.geometrycommands.DelaunayTriangulationCommand.DelaunayTriangulationOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The DelaunayTriangulationCommand UnitTest
 * @author Jared Erickson
 */
public class DelaunayTriangulationCommandTest {

    @Test 
    public void execute() throws Exception {
        
        String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))";
        DelaunayTriangulationOptions options = new DelaunayTriangulationOptions();
        options.setGeometry(inputGeometry);
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        DelaunayTriangulationCommand command = new DelaunayTriangulationCommand();
        command.execute(options, reader, writer);
        assertEquals("GEOMETRYCOLLECTION (POLYGON ((6.857126638942733 8.821305316892328, "
                + "8.017822881853032 7.492806794533148, 9.260874914207697 13.087320259444919, "
                + "6.857126638942733 8.821305316892328)), POLYGON ((9.260874914207697 "
                + "13.087320259444919, 8.017822881853032 7.492806794533148, 12.27256417862947 "
                + "12.73833434783841, 9.260874914207697 13.087320259444919)), POLYGON ((12.27256417862947 "
                + "12.73833434783841, 8.017822881853032 7.492806794533148, 13.737894633461437 "
                + "7.658802439672621, 12.27256417862947 12.73833434783841)))", writer.getBuffer().toString());
    }
}

package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ConvexHullCommand UnitTest
 * @author Jared Erickson
 */
public class ConvexHullCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
                + "(13.737894633461437 7.658802439672621), "
                + "(6.857126638942733 8.821305316892328), "
                + "(9.260874914207697 13.087320259444919), "
                + "(8.017822881853032 7.492806794533148))";
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        ConvexHullCommand command = new ConvexHullCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((8.017822881853032 7.492806794533148, "
                + "6.857126638942733 8.821305316892328, 9.260874914207697 "
                + "13.087320259444919, 12.27256417862947 12.73833434783841, "
                + "13.737894633461437 7.658802439672621, 8.017822881853032 "
                + "7.492806794533148))", writer.getBuffer().toString());
    }
}

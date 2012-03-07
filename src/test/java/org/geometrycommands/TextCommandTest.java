package org.geometrycommands;

import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import org.geometrycommands.TextCommand.TextOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The TextCommand UnitTest
 * @author Jared Erickson
 */
public class TextCommandTest {

    @Test
    public void execute() throws Exception {

        TextOptions options = new TextOptions();
        options.setText("J");

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        TextCommand command = new TextCommand();
        command.execute(options, reader, writer);
        Geometry geom = new WKTReader().read(writer.getBuffer().toString());
        assertNotNull(geom);
        assertTrue(geom instanceof Polygon);
    }
}

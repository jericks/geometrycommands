package org.geometrycommands;

import org.geometrycommands.ArcPolygonCommand.ArcPolygonOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ArcPolygonCommand UnitTest
 * @author Jared Erickson
 */
public class ArcPolygonCommandTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        ArcPolygonOptions options = new ArcPolygonOptions();
        options.setGeometry(inputGeometry);
        options.setStartAngle(45);
        options.setAngleExtent(90);
        options.setWidth(50);
        options.setHeight(50);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        ArcPolygonCommand command = new ArcPolygonCommand();
        command.execute(options, reader, writer);
        assertEquals("POLYGON ((125 125, 138.13304972044324 146.27258811335295, "
                + "121.38674369432238 149.73750955269105, "
                + "106.33112045049884 141.62747534553029, "
                + "100.01087342937183 125.73726063039155, "
                + "105.3833164538481 109.50207347255339, "
                + "119.93462372472202 100.51853837717505, "
                + "136.85607685018496 102.99015125625557, "
                + "148.2299398518295 115.76041697475566, "
                + "148.7342558247792 132.8539862772947, "
                + "138.13304972044324 146.27258811335295, "
                + "125 125))",
                writer.getBuffer().toString());
    }
}

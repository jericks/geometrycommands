package org.geometrycommands;

import org.geometrycommands.ArcPolygonCommand.ArcPolygonOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The ArcPolygonCommand UnitTest
 * @author Jared Erickson
 */
public class ArcPolygonCommandTest extends BaseTest {

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
        options.setDegrees(false);

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


    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "arcpoly",
                "-g", "POINT (100 100)",
                "-a", "45",
                "-e", "90",
                "-w", "50",
                "-h", "50",
                "-p", "10"
        }, null);
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
                + "125 125))", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "arcpoly",
                "-a", "45",
                "-e", "90",
                "-w", "50",
                "-h", "50",
                "-p", "10"
        }, "POINT (100 100)");
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
                + "125 125))", result);

        // Degrees
        result = runApp(new String[]{
                "arcpoly",
                "-a", "45",
                "-e", "90",
                "-w", "50",
                "-h", "50",
                "-p", "10",
                "-d"
        }, "POINT (100 100)");
        assertEquals("POLYGON ((125 125, 142.6776695296637 142.6776695296637, 139.33941090877616 145.4788011072248, " +
                "135.56545654351748 147.65769467591625, 131.47047612756302 149.1481456572267, " +
                "127.17889356869145 149.90486745229364, 122.82110643130855 149.90486745229364, " +
                "118.52952387243698 149.1481456572267, 114.43454345648252 147.65769467591625, " +
                "110.66058909122384 145.4788011072248, 107.32233047033631 142.6776695296637, 125 125))", result);
    }

}

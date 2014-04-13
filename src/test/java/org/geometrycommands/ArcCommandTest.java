package org.geometrycommands;

import org.geometrycommands.ArcCommand.ArcOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ArcCommand UnitTest
 * @author Jared Erickson
 */
public class ArcCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String inputGeometry = "POINT (100 100)";
        ArcOptions options = new ArcOptions();
        options.setGeometry(inputGeometry);
        options.setStartAngle(45);
        options.setAngleExtent(90);
        options.setWidth(50);
        options.setHeight(50);
        options.setNumberOfPoints(10);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        ArcCommand command = new ArcCommand();
        command.execute(options, reader, writer);
        assertEquals("LINESTRING (138.13304972044324 146.27258811335295, "
                + "121.38674369432238 149.73750955269105, "
                + "106.33112045049884 141.62747534553029, "
                + "100.01087342937183 125.73726063039155, "
                + "105.3833164538481 109.50207347255339, "
                + "119.93462372472202 100.51853837717505, "
                + "136.85607685018496 102.99015125625557, "
                + "148.2299398518295 115.76041697475566, "
                + "148.7342558247792 132.8539862772947, "
                + "138.13304972044324 146.27258811335295)",
                writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "arc",
                "-g", "POINT (100 100)",
                "-a", "45",
                "-e", "90",
                "-w", "50",
                "-h", "50",
                "-p", "10"
        }, null);
        assertEquals("LINESTRING (138.13304972044324 146.27258811335295, "
                + "121.38674369432238 149.73750955269105, "
                + "106.33112045049884 141.62747534553029, "
                + "100.01087342937183 125.73726063039155, "
                + "105.3833164538481 109.50207347255339, "
                + "119.93462372472202 100.51853837717505, "
                + "136.85607685018496 102.99015125625557, "
                + "148.2299398518295 115.76041697475566, "
                + "148.7342558247792 132.8539862772947, "
                + "138.13304972044324 146.27258811335295)", result);

        // Geometry from input stream
        result = runApp(new String[]{
                "arc",
                "-a", "45",
                "-e", "90",
                "-w", "50",
                "-h", "50",
                "-p", "10"
        }, "POINT (100 100)");
        assertEquals("LINESTRING (138.13304972044324 146.27258811335295, "
                + "121.38674369432238 149.73750955269105, "
                + "106.33112045049884 141.62747534553029, "
                + "100.01087342937183 125.73726063039155, "
                + "105.3833164538481 109.50207347255339, "
                + "119.93462372472202 100.51853837717505, "
                + "136.85607685018496 102.99015125625557, "
                + "148.2299398518295 115.76041697475566, "
                + "148.7342558247792 132.8539862772947, "
                + "138.13304972044324 146.27258811335295)", result);
    }

}

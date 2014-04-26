package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The VoronoiDiagramCommand UnitTest
 * @author Jared Erickson
 */
public class VoronoiDiagramCommandTest extends BaseTest {

    private final String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
            + "(13.737894633461437 7.658802439672621), "
            + "(6.857126638942733 8.821305316892328), "
            + "(9.260874914207697 13.087320259444919), "
            + "(8.017822881853032 7.492806794533148))";

    private final String resultGeometry = "GEOMETRYCOLLECTION (POLYGON ((-0.02364135557597 "
            + "1.6383514444727276, -0.02364135557597 15.508595261712216, "
            + "9.627541214926383 10.07049582452856, -0.02364135557597 "
            + "1.6383514444727276)), POLYGON ((-0.02364135557597 "
            + "15.508595261712216, -0.02364135557597 19.96808825396362, "
            + "11.584262944224145 19.96808825396362, 10.41703141452538 "
            + "9.895077997171324, 9.627541214926383 10.07049582452856, "
            + "-0.02364135557597 15.508595261712216)), "
            + "POLYGON ((-0.02364135557597 0.6120388000144441, "
            + "-0.02364135557597 1.6383514444727276, 9.627541214926383 "
            + "10.07049582452856, 10.41703141452538 9.895077997171324, "
            + "10.820040010594106 9.568190510788941, 11.079946222571074 "
            + "0.6120388000144441, -0.02364135557597 0.6120388000144441)), "
            + "POLYGON ((11.584262944224145 19.96808825396362, 20.61866262798014 "
            + "19.96808825396362, 20.61866262798014 12.394872259471132, "
            + "10.820040010594106 9.568190510788941, 10.41703141452538 "
            + "9.895077997171324, 11.584262944224145 19.96808825396362)), "
            + "POLYGON ((20.61866262798014 12.394872259471132, 20.61866262798014 "
            + "0.6120388000144441, 11.079946222571074 0.6120388000144441, "
            + "10.820040010594106 9.568190510788941, 20.61866262798014 "
            + "12.394872259471132)))";

    @Test
    public void execute() throws Exception {
        GeometryOptions options = new GeometryOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        VoronoiDiagramCommand command = new VoronoiDiagramCommand();
        command.execute(options, reader, writer);
        assertEquals(resultGeometry, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {

        // Geometry from options
        String result = runApp(new String[]{
                "voronoi",
                "-g", inputGeometry
        }, null);
        assertEquals(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "voronoi"
        }, inputGeometry);
        assertEquals(resultGeometry, result);
    }
}

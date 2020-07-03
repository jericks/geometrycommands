package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.geometrycommands.VoronoiDiagramCommand.VoronoiDiagramOptions;

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

    private final String resultGeometry = "GEOMETRYCOLLECTION (POLYGON ((-2.0109931788919377 -0.0979785219072582, " +
            "-2.0109931788919377 16.62839760229709, 9.627541214926385 10.070495824528559, " +
            "-2.0109931788919377 -0.0979785219072582)), " +
            "POLYGON ((-2.0109931788919377 16.62839760229709, -2.0109931788919377 21.95544007727959, " +
            "11.814551571917594 21.95544007727959, 10.41703141452538 9.895077997171324, " +
            "9.627541214926385 10.070495824528559, -2.0109931788919377 16.62839760229709)), " +
            "POLYGON ((-2.0109931788919377 -1.3753130233015236, -2.0109931788919377 -0.0979785219072582, " +
            "9.627541214926385 10.070495824528559, 10.41703141452538 9.895077997171324, " +
            "10.820040010594104 9.568190510788941, 11.137618881648764 -1.3753130233015236, " +
            "-2.0109931788919377 -1.3753130233015236)), " +
            "POLYGON ((11.814551571917594 21.95544007727959, 22.606014451296108 21.95544007727959, " +
            "22.606014451296108 12.968178462448074, 10.820040010594104 9.568190510788941, " +
            "10.41703141452538 9.895077997171324, 11.814551571917594 21.95544007727959)), " +
            "POLYGON ((22.606014451296108 12.968178462448074, 22.606014451296108 -1.3753130233015236, " +
            "11.137618881648764 -1.3753130233015236, 10.820040010594104 9.568190510788941, " +
            "22.606014451296108 12.968178462448074)))";

    @Test
    public void execute() throws Exception {
        VoronoiDiagramOptions options = new VoronoiDiagramOptions();
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

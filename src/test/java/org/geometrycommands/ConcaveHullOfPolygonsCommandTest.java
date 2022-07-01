package org.geometrycommands;

import org.geometrycommands.ConcaveHullOfPolygonsCommand.ConcaveHullOfPolygonsOptions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTReader;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ConcaveHullOfPolygonsCommand UnitTest
 * @author Jared Erickson
 */
public class ConcaveHullOfPolygonsCommandTest extends BaseTest {

    private final String polygonsWkt = "MULTIPOLYGON (" +
        "((-122.38889694213867 47.57595031143529, -122.3895299434662 47.57526633442543, -122.38845705986023 47.57515414686724, -122.38857507705688 47.57558480102301, -122.38889694213867 47.57595031143529))," +
        "((-122.38868772983551 47.57478501191467, -122.38879501819609 47.574224066564, -122.38778114318846 47.5742566377487, -122.38817274570465 47.574600443462614, -122.38868772983551 47.57478501191467))," +
        "((-122.3868852853775 47.57554137387374, -122.38756120204926 47.57528442917038, -122.38598942756653 47.575132433118526, -122.38690674304961 47.57534957020037, -122.3868852853775 47.57554137387374))" +
        ")";

    @Test
    public void execute() throws Exception {

        ConcaveHullOfPolygonsOptions options = new ConcaveHullOfPolygonsOptions();
        options.setGeometry(polygonsWkt);
        options.setMaximumEdgeLength(0.5);

        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        ConcaveHullOfPolygonsCommand command = new ConcaveHullOfPolygonsCommand();
        command.execute(options, reader, writer);
        Geometry concaveHullPolygon = new WKTReader().read(writer.toString());
        assertTrue(concaveHullPolygon instanceof Polygon);
        assertFalse(concaveHullPolygon.isEmpty());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "concavehullofpolygons",
                "-g", polygonsWkt,
                "-l", "0.5"
        }, null);
        Geometry concaveHullPolygon = new WKTReader().read(result);
        assertTrue(concaveHullPolygon instanceof Polygon);
        assertFalse(concaveHullPolygon.isEmpty());

        // Geometry from input stream
        result = runApp(new String[]{
                "concavehullofpolygons",
                "-l", "0.5"
        }, polygonsWkt);
        concaveHullPolygon = new WKTReader().read(result);
        assertTrue(concaveHullPolygon instanceof Polygon);
        assertFalse(concaveHullPolygon.isEmpty());

    }
}

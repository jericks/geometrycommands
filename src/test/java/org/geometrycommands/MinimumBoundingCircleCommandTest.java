package org.geometrycommands;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.geometrycommands.MinimumBoundingCircleCommand.MinimumBoundingCircleOptions;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.precision.GeometryPrecisionReducer;

/**
 * The MinimumBoundingCircleCommand UnitTest
 * @author Jared Erickson
 */
public class MinimumBoundingCircleCommandTest extends BaseTest {

    private final String inputGeometry = "MULTIPOINT ((12.27256417862947 12.73833434783841), "
            + "(13.737894633461437 7.658802439672621), "
            + "(6.857126638942733 8.821305316892328), "
            + "(9.260874914207697 13.087320259444919), "
            + "(8.017822881853032 7.492806794533148))";

    private final String resultGeometry = "POLYGON ((14.261208198913506 9.566699017816614, " +
            "14.189353574798158 8.83714677603589, 13.976551035360327 8.13563081776919, " +
            "13.630978462849573 7.489110007878018, 13.165916017428149 6.9224297785134485, " +
            "12.599235788063577 6.457367333092024, 11.952714978172406 6.111794760581269, " +
            "11.251199019905705 5.898992221143438, 10.521646778124982 5.82713759702809, " +
            "9.792094536344258 5.898992221143438, 9.090578578077558 6.111794760581269, " +
            "8.444057768186386 6.457367333092024, 7.877377538821817 6.922429778513448, " +
            "7.412315093400392 7.489110007878018, 7.066742520889637 8.135630817769188, " +
            "6.853939981451806 8.837146776035889, 6.782085357336458 9.566699017816614, " +
            "6.853939981451806 10.296251259597337, 7.066742520889636 10.997767217864038, " +
            "7.412315093400391 11.64428802775521, 7.877377538821815 12.210968257119779, " +
            "8.444057768186386 12.676030702541205, 9.090578578077555 13.021603275051959, " +
            "9.792094536344257 13.23440581448979, 10.521646778124982 13.306260438605138, " +
            "11.251199019905705 13.23440581448979, 11.952714978172407 13.021603275051959, " +
            "12.599235788063575 12.676030702541205, 13.165916017428147 12.21096825711978, " +
            "13.630978462849573 11.64428802775521, 13.976551035360327 10.997767217864041, " +
            "14.189353574798158 10.296251259597339, 14.261208198913506 9.566699017816614))";

    @Test
    public void execute() throws Exception {
        MinimumBoundingCircleOptions options = new MinimumBoundingCircleOptions();
        options.setGeometry(inputGeometry);

        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();

        MinimumBoundingCircleCommand command = new MinimumBoundingCircleCommand();
        command.execute(options, reader, writer);
        assertGeometriesSimilar(resultGeometry, writer.getBuffer().toString());
    }

    @Test
    public void run() throws Exception {
        // Geometry from options
        String result = runApp(new String[]{
                "mincircle",
                "-g", inputGeometry,
        }, null);
        assertGeometriesSimilar(resultGeometry, result);

        // Geometry from input stream
        result = runApp(new String[]{
                "mincircle"
        }, inputGeometry);
        assertGeometriesSimilar(resultGeometry, result);
    }

}
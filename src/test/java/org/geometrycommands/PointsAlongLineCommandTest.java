package org.geometrycommands;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.geometrycommands.PointsAlongLineCommand.PointsAlongLineOptions;
import static org.junit.Assert.*;

public class PointsAlongLineCommandTest extends BaseTest {

    @Test
    public void execute() throws Exception {

        String lineString = "LINESTRING (0 0, 10 10)";
        PointsAlongLineCommand cmd = new PointsAlongLineCommand();

        // Geometry from input stream
        PointsAlongLineOptions options = new PointsAlongLineOptions();
        options.setDistance(1);

        Reader reader = new StringReader(lineString);
        StringWriter writer = new StringWriter();

        cmd.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((0 0), (0.7071067811865475 0.7071067811865475), (1.414213562373095 1.414213562373095), " +
            "(2.1213203435596424 2.1213203435596424), (2.82842712474619 2.82842712474619), " +
            "(3.5355339059327373 3.5355339059327373), (4.242640687119285 4.242640687119285), " +
            "(4.949747468305833 4.949747468305833), (5.65685424949238 5.65685424949238), " +
            "(6.363961030678928 6.363961030678928), (7.071067811865475 7.071067811865475), " +
            "(7.778174593052023 7.778174593052023), (8.48528137423857 8.48528137423857), " +
            "(9.192388155425117 9.192388155425117), (9.899494936611665 9.899494936611665))", writer.toString());

        // Geometry from option
        options = new PointsAlongLineOptions();
        options.setDistance(2);
        options.setGeometry(lineString);

        reader = new StringReader(lineString);
        writer = new StringWriter();

        cmd.execute(options, reader, writer);
        assertEquals("MULTIPOINT ((0 0), (1.414213562373095 1.414213562373095), (2.82842712474619 2.82842712474619), " +
            "(4.242640687119285 4.242640687119285), (5.65685424949238 5.65685424949238), " +
            "(7.071067811865475 7.071067811865475), (8.48528137423857 8.48528137423857), " +
            "(9.899494936611665 9.899494936611665))", writer.toString());
    }

    @Test
    public void run() throws Exception {

        String lineString = "LINESTRING (0 0, 10 10)";

        // Geometry from options
        String result = runApp(new String[]{
            "pointsalong",
            "-g", lineString,
            "-d", "2"
        }, null);
        assertEquals("MULTIPOINT ((0 0), (1.414213562373095 1.414213562373095), (2.82842712474619 2.82842712474619), " +
            "(4.242640687119285 4.242640687119285), (5.65685424949238 5.65685424949238), " +
            "(7.071067811865475 7.071067811865475), (8.48528137423857 8.48528137423857), " +
            "(9.899494936611665 9.899494936611665))", result);

        // Geometry from input stream
        result = runApp(new String[]{
            "pointsalong",
            "-d", "1"
        }, lineString);
        assertEquals("MULTIPOINT ((0 0), (0.7071067811865475 0.7071067811865475), (1.414213562373095 1.414213562373095), " +
            "(2.1213203435596424 2.1213203435596424), (2.82842712474619 2.82842712474619), " +
            "(3.5355339059327373 3.5355339059327373), (4.242640687119285 4.242640687119285), " +
            "(4.949747468305833 4.949747468305833), (5.65685424949238 5.65685424949238), " +
            "(6.363961030678928 6.363961030678928), (7.071067811865475 7.071067811865475), " +
            "(7.778174593052023 7.778174593052023), (8.48528137423857 8.48528137423857), " +
            "(9.192388155425117 9.192388155425117), (9.899494936611665 9.899494936611665))", result);

    }

}

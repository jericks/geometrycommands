package org.geometrycommands;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.geometrycommands.NearestPointsCommand.NearestPointsOptions;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * The NearestPointsCommand Unit Test
 * @author Jared Erickson
 */
public class NearestPointsCommandTest {

    @Test
    public void execute() throws Exception {
        String wkt1 = "POLYGON ((90 90, 90 110, 110 110, 110 90, 90 90))";
        String wkt2 = "POLYGON ((173.96210441769105 -94.53669248798772, 193.14058991095382 -88.86344877872318, 198.81383362021836 -108.04193427198595, 179.6353481269556 -113.71517798125049, 173.96210441769105 -94.53669248798772))";
        NearestPointsCommand cmd = new NearestPointsCommand();
        NearestPointsOptions options = new NearestPointsOptions();
        options.setGeometry(wkt1);
        options.setOtherGeometry(wkt2);

        StringReader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        cmd.execute(options, reader, writer);
        String expected = "MULTIPOINT ((110 90), (173.96210441769105 -94.53669248798772))";
        String actual = writer.toString();
        assertEquals(expected, actual);
    }

}

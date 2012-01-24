package org.geometrycommands;

import org.geometrycommands.SimilarityCommand.SimilarityOptions;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The SimilarityCommand UnitTest
 * @author Jared Erickson
 */
public class SimilarityCommandTest {

    @Test 
    public void execute() throws Exception {
        
        // area
        String inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        String otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";
        SimilarityOptions options = new SimilarityOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setAlgorithm("area");
        
        Reader reader = new StringReader(inputGeometry);
        StringWriter writer = new StringWriter();
        
        SimilarityCommand command = new SimilarityCommand();
        command.execute(options, reader, writer);
        assertEquals("0.35555555555555557", writer.getBuffer().toString());
        
        // hausdorff
        inputGeometry = "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))";
        otherGeometry = "POLYGON ((2 2, 2 14, 14 14, 14 2, 2 2))";
        options = new SimilarityOptions();
        options.setGeometry(inputGeometry);
        options.setOtherGeometry(otherGeometry);
        options.setAlgorithm("hausdorff");
        
        reader = new StringReader(inputGeometry);
        writer = new StringWriter();
        
        command.execute(options, reader, writer);
        assertEquals("0.7142857142857142", writer.getBuffer().toString());
    }
}

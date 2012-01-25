package org.geometrycommands;

import org.junit.After;
import org.junit.Before;
import java.security.Permission;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The App UnitTest
 * @author Jared Erickson
 */
public class AppTest {

    private static final String NEW_LINE = System.getProperty("line.separator");

    @Before
    public void before() throws Exception {
        System.setSecurityManager(new OverrideExitSecurityManager());
    }

    @After
    public void after() throws Exception {
        System.setSecurityManager(null);
    }

    @Test
    public void testNoCommandName() {
        CaptureOutput capture = CaptureOutput.createAndStart();
        try {
            App.main(new String[]{});
        } catch (OverrideExitException ex) {
        }
        Map<String, String> values = capture.stop();
        assertEquals("Please enter a geometry command!" + NEW_LINE + "Usage: geom <command> <args>", values.get("err"));
    }

    @Test
    public void testUnknownCommandName() {
        CaptureOutput capture = CaptureOutput.createAndStart();
        try {
            App.main(new String[]{"ZZZ"});
        } catch (OverrideExitException ex) {
        }
        Map<String, String> values = capture.stop();
        assertEquals("Unknown geometry command: 'ZZZ'!" + NEW_LINE + "Usage: geom <command> <args>", values.get("err"));
    }

    @Test
    public void testHelp() {
        CaptureOutput capture = CaptureOutput.createAndStart();
        try {
            App.main(new String[]{
                        "centroid", "--help"
                    });
        } catch (OverrideExitException ex) {
        }
        Map<String, String> values = capture.stop();
        assertEquals("--help    : Print help message" + NEW_LINE + " -geom VAL : The input geometry", values.get("out"));
    }

    @Test
    public void testArgumentError() {
        CaptureOutput capture = CaptureOutput.createAndStart();
        try {
            App.main(new String[]{
                        "centroid", "--asdf"
                    });
        } catch (OverrideExitException ex) {
        }
        Map<String, String> values = capture.stop();
        assertEquals("\"--asdf\" is not a valid option" + NEW_LINE
                + "Usage: geom <command> <args>" + NEW_LINE
                + " --help    : Print help message" + NEW_LINE
                + " -geom VAL : The input geometry", values.get("err"));
    }

    @Test
    public void testCentroid() {
        CaptureOutput capture = CaptureOutput.createAndStart();
        try {
            App.main(new String[]{
                        "centroid", "-geom", "POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))"
                    });
        } catch (OverrideExitException ex) {
        }
        Map<String, String> values = capture.stop();
        assertEquals("POINT (5 5)", values.get("out"));
    }

    @Test
    public void testEnvelopeWithExpandBy() {
        CaptureOutput capture = CaptureOutput.createAndStart();
        try {
            App.main(new String[]{
                        "envelope", "-geom", "POINT (5 5)", "-expandBy", "5"
                    });
        } catch (OverrideExitException ex) {
        }
        Map<String, String> values = capture.stop();
        assertEquals("POLYGON ((0 0, 0 10, 10 10, 10 0, 0 0))", values.get("out"));
    }

    protected static class OverrideExitException extends SecurityException {

        public final int status;

        public OverrideExitException(int status) {
            super("Override exit");
            this.status = status;
        }
    }

    private static class OverrideExitSecurityManager extends SecurityManager {

        @Override
        public void checkPermission(Permission perm) {
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
        }

        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            throw new OverrideExitException(status);
        }
    }
}

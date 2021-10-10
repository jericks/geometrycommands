package org.geometrycommands;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {

    @Test
    public void find() {
        assertTrue(Commands.find("buffer").isPresent());
        assertTrue(Commands.find("centroid").isPresent());
        assertFalse(Commands.find("asdfasd").isPresent());
    }

    @Test
    public void getAll() {
        List<Command> commands = Commands.getAll();
        assertFalse(commands.isEmpty());
    }

}

package io.github.willena.maven.plugins.githooks;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CommitFollowConventionalTest {

    @Test
    void runWithValidMessage() throws URISyntaxException {
        Path path = Path.of(getClass().getResource("conventional-commit-message.txt").toURI());
        assertDoesNotThrow(() -> new CommitFollowConventional().run(null, new String[]{path.toString()}));
    }

    @Test
    void runWithInvalidMessage() throws URISyntaxException {
        Path path = Path.of(getClass().getResource("non-conventional-commit.txt").toURI());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new CommitFollowConventional().run(null, new String[]{path.toString()}));
        assertTrue(ex.getMessage().contains("Git commit message does not follow conventional commit convention"));
    }
    @Test
    void runWithInvalidInputs() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new CommitFollowConventional().run(null, new String[]{}));
        assertTrue(ex.getMessage().contains("Commit file argument required"));
    }
}
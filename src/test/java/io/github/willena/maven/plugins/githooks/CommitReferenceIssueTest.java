package io.github.willena.maven.plugins.githooks;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CommitReferenceIssueTest {

    @Test
    void workingWithMultipleFormat() throws URISyntaxException {
        CommitReferenceIssue cri = new CommitReferenceIssue();
        Path path = Path.of(getClass().getResource("commit-message-with-issues.txt").toURI());
        assertDoesNotThrow(() -> cri.run(null, new String[]{path.toString()}));
    }

    @Test
    void failWhenNoIssueReference() throws URISyntaxException {
        CommitReferenceIssue cri = new CommitReferenceIssue();
        Path path = Path.of(getClass().getResource("commit-message-no-issues-reference.txt").toURI());
        assertThrows(IllegalArgumentException.class, () -> cri.run(null, new String[]{path.toString()}));
    }

    @Test
    void runWithInvalidInputs() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new CommitReferenceIssue().run(null, new String[]{}));
        assertTrue(ex.getMessage().contains("Commit file argument required"));
    }
}
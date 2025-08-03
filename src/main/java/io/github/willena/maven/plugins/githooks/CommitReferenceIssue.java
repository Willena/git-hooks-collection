package io.github.willena.maven.plugins.githooks;

import org.eclipse.sisu.Description;

import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check if commit message contains an issue reference.
 * Applicability: commit-msg
 */
@Singleton
@Named("CommitReferenceIssue")
@Description("Check if commit message contains an issue reference.")
public class CommitReferenceIssue implements RunnableGitHook {
    private static final Pattern ISSUE_REFERENCE_PATTERN = Pattern.compile("(?<jiraLike>\\b[A-Za-z][A-Za-z0-9_]+-[1-9][0-9]*)|(?<issueId>#\\d+)", Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);

    @Override
    public void run(HookContext context, String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("Commit file argument required");
        }
        String commitMessage = Files.readString(Path.of(args[0]));
        Matcher matcher = ISSUE_REFERENCE_PATTERN.matcher(commitMessage);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Git commit message must reference an issue");
        }
    }
}

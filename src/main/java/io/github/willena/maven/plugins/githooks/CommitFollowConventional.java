package io.github.willena.maven.plugins.githooks;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check if commit message follows the conventional commit format.
 * Applicability: commit-msg
 */
public class CommitFollowConventional implements RunnableGitHook {

    // Adapted from https://regex101.com/r/vcxVpP/5
    private static final Pattern CONVENTIONAL_PATTERN = Pattern.compile("\\A(?:(?:^(?<type>feat|fix|ci|chore|docs|test|style|refactor|build|perf|revert)(?:\\((?<scope>[\\w-]+)\\))?(?<breaking>!)?:\\s(?<subject>(?:\\b|[\\[\\]])[\\w#<> ./\\t\\\\\\-\\[\\]\\(\\)]{3,}(?:\\b|\\.|[\\[\\]]))$)(?:(?:(?<BLANKLINE>\\n^$\\n)(?<body>(?:^.{3,}(?:\\b|\\.)$\\n?){1,3}))?(?<BLANKLINE2>\\n^$\\n)(?:(?<breakingchange>^BREAKING\\sCHANGE:\\s[\\s\\w]+(?:\\b|\\.)$)\\n)?(?:(?<footer>^\\w+:\\s[\\w#-]+(?:\\b|\\.))))?\\n?|)\\Z",Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS );

    @Override
    public void run(HookContext context, String[] args) throws Exception {
        if (args.length == 0){
            throw new IllegalArgumentException("Commit file argument required");
        }
        String commitMessage = Files.readString(Path.of(args[0])).replaceAll("\r", "");
        Matcher matcher = CONVENTIONAL_PATTERN.matcher(commitMessage);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Git commit message does not follow conventional commit convention");
        }
    }
}

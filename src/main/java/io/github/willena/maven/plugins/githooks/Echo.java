package io.github.willena.maven.plugins.githooks;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;

/**
 * Hook that prints its inputs
 * Applicability: any hooks
 */
@Named("Echo")
@Singleton
public class Echo implements RunnableGitHook {

    private void log(Log log, String message) {
        if (log != null) {
            log.info(message);
        } else {
            System.out.println("INFO: " + message);
        }
    }

    @Override
    public void run(HookContext context, String[] args) throws Exception {

        log(context.getLogger(), "Project: " + context.getProject().toString());

        log(context.getLogger(), "Hook arguments: " + Arrays.toString(args));

        log(context.getLogger(), "Environment");
        System.getenv().entrySet().forEach(e -> log(context.getLogger(), e.getKey() + ": " + e.getValue()));

        log(context.getLogger(), "Properties");
        System.getProperties().entrySet().forEach(e -> log(context.getLogger(), e.getKey() + ": " + e.getValue()));
    }
}

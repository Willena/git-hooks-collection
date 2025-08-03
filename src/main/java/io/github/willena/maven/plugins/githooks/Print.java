package io.github.willena.maven.plugins.githooks;

import org.apache.maven.plugin.logging.Log;
import org.eclipse.sisu.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;

/**
 * Hook that prints its inputs
 * Applicability: any hooks
 */
@Singleton
@Named("Print")
@Description("Hook that prints its inputs")
public class Print implements RunnableGitHook {

    private static final Logger LOGGER = LoggerFactory.getLogger(Print.class);

    private void log(Log log, String message) {
        if (log != null) {
            log.info(message);
        } else {
            LOGGER.info(message);
        }
    }

    @Override
    public void run(HookContext context, String[] args) throws Exception {

        log(context.getLogger(), "Project: " + context.getProject().toString());

        log(context.getLogger(), "Hook arguments: " + Arrays.toString(args));

        log(context.getLogger(), "Environment");
        System.getenv().forEach((key, value) -> log(context.getLogger(), key + ": " + value));

        log(context.getLogger(), "Properties");
        System.getProperties().forEach((key, value) -> log(context.getLogger(), key + ": " + value));
    }
}

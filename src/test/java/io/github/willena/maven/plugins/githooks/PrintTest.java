package io.github.willena.maven.plugins.githooks;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {

    private class FakeLog implements Log {

        LinkedList<CharSequence> messages = new LinkedList<>();

        @Override
        public boolean isDebugEnabled() {
            return false;
        }

        @Override
        public void debug(CharSequence charSequence) {
            messages.add(charSequence);
        }

        @Override
        public void debug(CharSequence charSequence, Throwable throwable) {
            messages.add(charSequence);
        }

        @Override
        public void debug(Throwable throwable) {
        }

        @Override
        public boolean isInfoEnabled() {
            return false;
        }

        @Override
        public void info(CharSequence charSequence) {
            messages.add(charSequence);
        }

        @Override
        public void info(CharSequence charSequence, Throwable throwable) {
            messages.add(charSequence);
        }

        @Override
        public void info(Throwable throwable) {
        }

        @Override
        public boolean isWarnEnabled() {
            return false;
        }

        @Override
        public void warn(CharSequence charSequence) {
            messages.add(charSequence);
        }

        @Override
        public void warn(CharSequence charSequence, Throwable throwable) {
            messages.add(charSequence);
        }

        @Override
        public void warn(Throwable throwable) {

        }

        @Override
        public boolean isErrorEnabled() {
            return false;
        }

        @Override
        public void error(CharSequence charSequence) {
            messages.add(charSequence);
        }

        @Override
        public void error(CharSequence charSequence, Throwable throwable) {
            messages.add(charSequence);
        }

        @Override
        public void error(Throwable throwable) {

        }
    }

    @Test
    void shouldNotFail() {
        FakeLog fakeLog = new FakeLog();
        HookContext ctx = new HookContext(new MavenProject(), null, fakeLog);
        Print printHook = new Print();
        assertDoesNotThrow(() -> printHook.run(ctx, new String[]{"abc"}));

        assertTrue(fakeLog.messages.size() > 4);
    }

}
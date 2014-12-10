package com.asseco.aha.training.spring_advanced.core.testing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { PackageConfig.class })
public class LoggingTest {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(LoggingTest.class);

    @Autowired
    private Calc calc;

    /**
     * Log method execution (start & stop).
     */
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            LOG.debug("Starting test {}#{}", description.getTestClass().getSimpleName(), description.getMethodName());
        }

        protected void finished(Description description) {
            LOG.debug("Finished test {}#{}", description.getTestClass().getSimpleName(), description.getMethodName());
        }

        protected void skipped(AssumptionViolatedException e, Description description) {
            LOG.debug("Skipped test {}#{}", description.getTestClass(), description.getMethodName());
        }

    };

    @Test
    public void testOk() {
        assertThat(5 + 5, equalTo(10));
    }

    @Test
    public void testFailure() {
        assertThat(5 + 6, not(equalTo(10)));
    }
}

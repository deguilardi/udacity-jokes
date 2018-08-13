package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest extends ApplicationTestCase<Application> {

    private static final Integer JOKE_ID = 1;
    private CountDownLatch mSignal;

    public JokeAsyncTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mSignal.countDown();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testJokeIsFetced() throws Throwable {
        JokeTask task = new JokeTask(new JokeTask.Listener() {
            @Override
            public void onComplete(String joke) {
                assertTrue("Joke can't be empty nor null", !TextUtils.isEmpty(joke));
            }
        });
        task.execute(JOKE_ID);
    }
}
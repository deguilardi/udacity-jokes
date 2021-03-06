package com.udacity.gradle.builditbigger;

import android.icu.util.TimeUnit;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest extends ActivityTestCase{

    private static final Integer JOKE_ID = 1;
    private CountDownLatch mSignal = new CountDownLatch(1);

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
                mSignal.countDown();
            }
        });

        task.execute(JOKE_ID);
        mSignal.await(10, java.util.concurrent.TimeUnit.SECONDS);
        String joke = task.get();
        assertTrue("Joke can't be empty nor null", !TextUtils.isEmpty(joke));
    }
}
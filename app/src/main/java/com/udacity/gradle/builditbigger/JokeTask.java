package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeTask extends AsyncTask<Integer, Void, String> {

    private static final String LOCALHOST = "http://10.0.2.2:8080/_ah/api/";
    private Listener mListener;
    private MyApi mMyApi;

    public interface Listener {
        void onComplete(String result);
    }

    public JokeTask(Listener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(Integer... params) {

        if (mMyApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(LOCALHOST)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            mMyApi = builder.build();
        }

        Integer jokeId = params[0];

        try {
            return (mMyApi.getJoke(jokeId).execute().getData());
        } catch (IOException ioe) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if(!TextUtils.isEmpty(joke)) {
            mListener.onComplete(joke);
        }
    }
}

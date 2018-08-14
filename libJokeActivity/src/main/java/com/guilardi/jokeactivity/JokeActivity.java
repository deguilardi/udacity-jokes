package com.guilardi.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "EXTRA_JOKE";
    TextView mJokeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_JOKE)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String joke = extras.getString(EXTRA_JOKE);
                mJokeView = findViewById(R.id.joke);
                mJokeView.setText(joke);
            }
        }
    }
}

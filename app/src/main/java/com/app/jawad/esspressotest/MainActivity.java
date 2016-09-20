package com.app.jawad.esspressotest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.core.deps.guava.annotations.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements MessageDelayer.DelayerCallback, View.OnClickListener {

    private static final String STRING_TO_BE_TYPED = "login successfully";


    public Button mBtn;
    // The Idling Resource which will be null in production.
    @Nullable
    private SimpleIdlingResource mIdlingResource;
    private EditText mEtEmail;
    private EditText mEtPassword;
    private TextView mTvLoginStatus;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEtEmail = (EditText) findViewById(R.id.et_email);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvLoginStatus = (TextView) findViewById(R.id.tv_login_status);
        mBtn = (Button) findViewById(R.id.btn_login);
        mBtn.setOnClickListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    public void onDone(String text) {
        // The delayer notifies the activity via a callback.
        Toast.makeText(MainActivity.this,"Text has been change to \" "+text+"\" ", Toast.LENGTH_SHORT).show();
        mTvLoginStatus.setText(STRING_TO_BE_TYPED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Toast.makeText(MainActivity.this,"Button Clicked!", Toast.LENGTH_SHORT).show();
                MessageDelayer.processMessage("Espresso", MainActivity.this, mIdlingResource);
                mTvLoginStatus.setText("logging you in. please wait!");
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}

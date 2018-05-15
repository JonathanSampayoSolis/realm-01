package com.example.jjsampayo.realm_01;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jjsampayo.realm_01.presentation.add.AddDialogFragment;
import com.example.jjsampayo.realm_01.presentation.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName().toUpperCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.fab).setOnClickListener(view -> AddDialogFragment.newInstance()
                .show(getSupportFragmentManager(), TAG));

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                .add(R.id.act_main_layout, MainFragment.newInstance())
                .commit();
    }

}

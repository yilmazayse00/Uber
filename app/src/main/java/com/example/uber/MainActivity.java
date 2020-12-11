package com.example.uber;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button btn = (Button) findViewById(R.id.button);
    }

    public void on_Click(View view) {
        Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
        startActivity(intent);
        finish();
    }
}
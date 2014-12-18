package com.example.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedActivity extends Activity {

    TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        messageView = (TextView) findViewById(R.id.messageView);

        Bundle extras = getIntent().getExtras();
        String message;
        if (extras != null) {
            message = extras.getString("message");
        } else {
            message = "Erro!";
        }

        messageView.setText(message);

        Button b = (Button) findViewById(R.id.button2);
        b.setVisibility(View.VISIBLE);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
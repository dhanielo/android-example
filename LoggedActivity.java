# LoggedActivity
package com.example.android;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
      if (extras != null) {
		  String message = extras.getString("message");
	  } else {
		  String message = "Erro!";
	  }
      
      messageView.setText((CharSequence)message);
	  
	  Button b = (Button)findViewById(R.id.button2);
      b.setVisibility(View.VISIBLE);
	  b.setOnClickListener(this);
   }
   
   public void goBack(View view)
   {
	    Intent intent = new Intent(getApplicationContext(),com.example.android.MainActivity.class);
		startActivity(intent);   
   }
   
   public void onClick(View v)
   {
	   this.goBack(v);
   }
}
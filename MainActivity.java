#MainActivity

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

public class MainActivity extends Activity {

   private DBHelper mydb;
   TextView login;
   TextView senha;
   
    @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       login = (TextView) findViewById(R.id.editTextLogin);
       senha = (TextView) findViewById(R.id.editTextSenha);

       mydb = new DBHelper(this);

       login.setText((CharSequence)login);
       login.setFocusable(false);
       login.setClickable(false);

       senha.setText((CharSequence)senha);
       senha.setFocusable(false); 
       senha.setClickable(false);
	   
	   Button b = (Button)findViewById(R.id.button1);
       b.setVisibility(View.VISIBLE);
	   b.setOnClickListener(this);
   }
   
   public void login(View view)
   {	 
        Bundle dataBundle = new Bundle();
		if (mydb.checkLogin(login.getText().toString(), senha.getText().toString()))
		{
			dataBundle.putString("message", "Usuário Autenticado!");
		} else if (mydb.getDataByLogin(login.getText().toString())) {
			dataBundle.putString("message", "Usuário Já Existe!");
		} else {
			if (mydb.insertUsuario(login.getText().toString(), senha.getText().toString())) {
				dataBundle.putString("message", "Usuário Inserido Com Sucesso!");
			} else {
				dataBundle.putString("message", "Erro ao Inserir Usuário.");
			}
		}
        Intent intent = new Intent(getApplicationContext(),com.example.android.LoggedActivity.class);
		intent.putExtras(dataBundle);
        startActivity(intent);      
   }
   
   public void onClick(View v) {
	   this.login(v);
   }
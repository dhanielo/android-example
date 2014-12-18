package com.example.android;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private DBHelper mydb;
    EditText login;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.editTextLogin);
        senha = (EditText) findViewById(R.id.editTextSenha);

        mydb = new DBHelper(this);

        Button b = (Button) findViewById(R.id.button1);
        b.setVisibility(View.VISIBLE);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MainActivity.this.login(v);
            }
        });
    }

    public void login(View view) {

        Bundle dataBundle = new Bundle();
        String login_text = login.getText().toString();
        String login_pass = senha.getText().toString();

        if (mydb.checkLogin(login_text, login_pass)) {
            dataBundle.putString("message", "Usuário Autenticado!");
        } else if (mydb.checkOnlyLogin(login.getText().toString())) {
            dataBundle.putString("message", "Usuário Já Existe!");
        } else {
            if (mydb.insertUsuario(login.getText().toString(), senha.getText().toString())) {
                dataBundle.putString("message", "Usuário Inserido Com Sucesso!");
            } else {
                dataBundle.putString("message", "Erro ao Inserir Usuário.");
            }
        }
        Intent intent = new Intent(getApplicationContext(), com.example.android.LoggedActivity.class);
        intent.putExtras(dataBundle);
        startActivity(intent);
    }
}
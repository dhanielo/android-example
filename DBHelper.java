#DBHelper

package com.example.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "AndroidSample.db";
   public static final String USUARIO_TABLE_NAME = "usuario";
   public static final String USUARIO_COLUMN_ID = "id";
   public static final String USUARIO_COLUMN_LOGIN = "login";
   public static final String USUARIO_COLUMN_SENHA = "senha";

   private HashMap hp;

   public DBHelper(Context context)
   {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
      db.execSQL(
      "create table usuario " +
      "(id integer primary key, login text,senha text)"
      );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS usuario");
      onCreate(db);
   }

   public boolean insertUsuario(String login, String senha)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("login", login);
      contentValues.put("senha", senha);

      db.insert("usuario", null, contentValues);
      return true;
   }
   
   public Cursor getData(int id){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from usuario where id="+id+"", null );
      return res;
   }
   
   public Cursor getDataByLogin(String login){
	  SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from usuario where login="+login+"", null );
      return res   
   }
  
   public boolean checkLogin(String login, String senha)
   {
	    SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from usuario where login="+login+" and senha="+senha+"", null );
        if (res) {
			return true;
		}
		return false;
   }
}
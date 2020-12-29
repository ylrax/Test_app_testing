package com.test.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BdTestActivity extends AppCompatActivity {

    private TextView tv_show;
    private EditText ed1, ed2, ed3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd_test);

        tv_show = (TextView)findViewById(R.id.textView4);
        ed1 = (EditText)findViewById(R.id.editTextNumber);
        ed2 = (EditText)findViewById(R.id.editTextTextPersonName);
        ed3 = (EditText)findViewById(R.id.editTextTextPersonName2);
    }

    public void insertar(View view){
        AdminSQlLiteHelper admin = new AdminSQlLiteHelper(this, "demo", null, 1);
        SQLiteDatabase DB = admin.getWritableDatabase();

        String id = ed1.getText().toString();
        String descr = ed2.getText().toString();
        String valores = ed3.getText().toString();

        if(!id.isEmpty() && !descr.isEmpty() && !valores.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("descript", descr);
            registro.put("value", valores);

            DB.insert("demo", null, registro);
            DB.close();

        } else{
            Toast.makeText(this, "valores vacios", Toast.LENGTH_SHORT).show();
        }
    }


    public void mostrar(View view){
        AdminSQlLiteHelper admin = new AdminSQlLiteHelper(this, "demo", null, 1);
        SQLiteDatabase DB = admin.getWritableDatabase();
        Cursor fila = DB.rawQuery("select * from demo", null);

        //revisar esto
        if (fila.moveToFirst()){
            int count = fila.getCount();
            Toast.makeText(this, Integer.toString(count), Toast.LENGTH_SHORT).show();
            String row = fila.getString(0) + " " +  fila.getString(1) + " " +  fila.getString(2);
            while(fila.moveToNext()){
                row += "\n" + fila.getString(0) + " " + fila.getString(1) + " " + fila.getString(2);
            }
            tv_show.setText(row);
        } else{
            Toast.makeText(this, "nada", Toast.LENGTH_SHORT).show();
            tv_show.setText("");
        }
        fila.close();
        DB.close();
        //admin.close();
    }


    public void vaciar(View view){
        AdminSQlLiteHelper admin = new AdminSQlLiteHelper(this, "demo", null, 1);
        SQLiteDatabase DB = admin.getWritableDatabase();
        String id_to_delete = ed1.getText().toString();
        DB.delete("demo", "id = " + id_to_delete, null);
        Toast.makeText(this, "deleted " + id_to_delete, Toast.LENGTH_SHORT).show();
        DB.close();
        //admin.close();

    }


    public void volver(View view){
        Intent anter = new Intent(this, MainActivity.class);
        startActivity(anter);
    }


}
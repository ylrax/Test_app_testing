package com.test.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;
    private TextView tv2;
    private String def1, def2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Oncreate first", Toast.LENGTH_SHORT).show();
        // La actividad está creada

        //poner icono en la barra superior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo_round);

        et1 = (EditText) findViewById(R.id.editTextNumber2);
        et2 = (EditText) findViewById(R.id.editTextNumber3);
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);

        def1 = "Mayor que 10";
        def2 = "menor que 10";
    }

    // poner barra de puntitos al empezar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manupuntitos, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.Item1){
            Toast.makeText(this, "Opcion 1", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.Item2){
            Toast.makeText(this, "Opcion 2", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.buscar) {
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void do_something(View vista1){
        String value1 = et1.getText().toString();
        String value2 = et2.getText().toString();

        int numero1 = Integer.parseInt(value1);
        int numero2 = Integer.parseInt(value2);

        int suma = numero1 + numero2;

        String res = String.valueOf(suma);

        tv1.setText(res);
        if (suma>10){
            tv2.setText(def1);
        } else {
            tv2.setText(def2);
        }


    }

    public void next(View view){
        Intent siguiente = new Intent(this, secondActivity.class);
        siguiente.putExtra("dato", et1.getText().toString());
        startActivity(siguiente);
        // se cerraria la main poniendo:
        //finish();
    }

    public void next2(View view){
        Intent siguiente = new Intent(this, BdTestActivity.class);
        startActivity(siguiente);
        // se cerraria la main poniendo:
        //finish();
    }

}
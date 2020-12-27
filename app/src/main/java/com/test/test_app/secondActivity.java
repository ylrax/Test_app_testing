package com.test.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {

    private TextView tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(this, "Oncreate second", Toast.LENGTH_SHORT).show();

        String dato = getIntent().getStringExtra("dato");
        //Toast.makeText(this, dato, Toast.LENGTH_SHORT).show();
        tv3 = (TextView)findViewById(R.id.textView3);
        tv3.setText("Valor es" + dato);
    }




    public void anterior(View view){
        Intent anter = new Intent(this, MainActivity.class);
        startActivity(anter);
    }
}
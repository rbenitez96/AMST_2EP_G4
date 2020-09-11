package com.example.examenpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnBuscarHeroes;
    private EditText edxtIdHeroe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuscarHeroes = findViewById(R.id.btnBuscarheroes);
        edxtIdHeroe = findViewById(R.id.EdtxIdHeroe);
    }

    public void buscarheroes(View view){
        if(TextUtils.isEmpty(edxtIdHeroe.getText().toString().trim())){
            edxtIdHeroe.setError("Este campo no puede estar vacio");
        }else{
            String id = edxtIdHeroe.getText().toString().trim();
            Intent heroe = new Intent(this,Heroes.class);
            heroe.putExtra("id",id);
            startActivity(heroe);
        }
    }
}
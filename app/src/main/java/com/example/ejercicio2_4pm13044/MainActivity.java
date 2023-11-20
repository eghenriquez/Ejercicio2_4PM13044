package com.example.ejercicio2_4pm13044;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnregistrar,btnlista,btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnregistrar=(Button) findViewById(R.id.btnregistrar);
        btnlista=(Button) findViewById(R.id.btnlista);
        btnsalir=(Button) findViewById(R.id.btnsalir);


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crear intent para llamar a la actividad de captura de firmas*/
                Intent intentfirma = new Intent(getApplicationContext(), ActivityRegistrar.class);
                startActivity(intentfirma);

            }
        });

        btnlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crear intent para llamar a la actividad de listar firmas*/
                Intent intentlista = new Intent(getApplicationContext(), ActivityLista.class);
                startActivity(intentlista);

            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crear intent para llamar a la actividad de salir del programa*/
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
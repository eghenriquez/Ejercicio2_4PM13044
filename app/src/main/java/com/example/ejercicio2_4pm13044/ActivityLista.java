package com.example.ejercicio2_4pm13044;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ejercicio2_4pm13044.Conexion.SQLliteconexion;
import com.example.ejercicio2_4pm13044.Operaciones.Firmas;
import com.example.ejercicio2_4pm13044.Operaciones.Transacciones;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {
    SQLliteconexion conexion = new SQLliteconexion(this, Transacciones.NameDatabase, null, 1);

    Button btnregresa2;
    ArrayList<Firmas> listafirmas= new ArrayList<Firmas>();
    ImageView imageView;
    ListView listaF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        btnregresa2=(Button) findViewById(R.id.btnregresa2);

        SQLiteDatabase db = conexion.getWritableDatabase();
        String sql = "SELECT * FROM Signature";
        Cursor cursor = db.rawQuery(sql, new String[] {});

        while (cursor.moveToNext()){
            listafirmas.add(new Firmas(cursor.getString(0) , cursor.getBlob(1)));
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        AdaptadorFirmas adaptador = new AdaptadorFirmas(this);
        listaF = findViewById(R.id.listaF);
        listaF.setAdapter(adaptador);

        listaF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                obtenerFoto(i);
            }
        });

        btnregresa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crear intent para regresar a la actividad principal*/
                Intent intentregresa = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentregresa);
            }
        });
    }

    private void obtenerFoto( int id) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Firmas lista_Firmas = null;
        listafirmas = new ArrayList<Firmas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.TbSignature,null);

        while (cursor.moveToNext())
        {
            lista_Firmas = new Firmas();
            lista_Firmas.setDescripcion(cursor.getString(0));
            listafirmas.add(lista_Firmas);
        }
        cursor.close();
        Firmas signature = listafirmas.get(id);
    }

    class AdaptadorFirmas extends ArrayAdapter<Firmas> {

        AppCompatActivity appCompatActivity;

        AdaptadorFirmas(AppCompatActivity context) {
            super(context, R.layout.firma, listafirmas);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.firma, null);

            imageView = item.findViewById(R.id.imagFirma);

            SQLiteDatabase db = conexion.getWritableDatabase();

            String sql = "SELECT * FROM Signature";

            Cursor cursor = db.rawQuery(sql, new String[] {});
            Bitmap bitmap = null;
            TextView textView1 = item.findViewById(R.id.DescripcionImagen);

            if (cursor.moveToNext()){
                textView1.setText(listafirmas.get(position).getDescripcion());
                byte[] blob = listafirmas.get(position).getImage();
                ByteArrayInputStream bais = new ByteArrayInputStream(blob);
                bitmap = BitmapFactory.decodeStream(bais);
                imageView.setImageBitmap(bitmap);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            db.close();

            return(item);
        }
    }
}
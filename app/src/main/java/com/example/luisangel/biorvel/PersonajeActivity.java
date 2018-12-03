package com.example.luisangel.biorvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class PersonajeActivity extends AppCompatActivity {
    Bundle bundle = getIntent().getExtras();
    TextView nombre;
    TextView descripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        //creando los views
        nombre = (TextView) findViewById(R.id.nombre);
        descripcion = (TextView) findViewById(R.id.descripcion);
        imagen = (ImageView) findViewById(R.id.imagenP);

        //agregando datos a los views
        nombre.setText(bundle.getString("nombreKey"));
        descripcion.setText(bundle.getString("descripcionKey"));

        //agregando imagen al ImageView
        Glide.with(this)
                .load(bundle.getString("imagenKey"))
                .into(imagen);
    }
}

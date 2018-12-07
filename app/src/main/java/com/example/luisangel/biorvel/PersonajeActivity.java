package com.example.luisangel.biorvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class PersonajeActivity extends AppCompatActivity {
    Bundle bundle;// = getIntent().getExtras();
    TextView nombre;
    TextView descripcion;
    TextView pelicula;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);
        bundle = getIntent().getExtras();

        //creando los views
        nombre = (TextView) findViewById(R.id.nombre);
        descripcion = (TextView) findViewById(R.id.descripcion);
        imagen = (ImageView) findViewById(R.id.imagenP);
        pelicula = (TextView) findViewById(R.id.peliculasP);

        //agregando datos a los views
        nombre.setText(bundle.getString("nombreKey"));

        String seteo = "Un gran dia para morir";

        if(!(bundle.getString("peliculasKey") == "")) {
            String[] peliculas = bundle.getString("peliculasKey").split("/");

            seteo = "Movies: \n";
            for (int i = 0; i < peliculas.length; ++i) {
                seteo = seteo + peliculas[i] + "\n";
            }
        }


        pelicula.setText(seteo);
        descripcion.setText(bundle.getString("descripcionKey"));

        //Log.d("PersonajeActivity",seteo);

        //agregando imagen al ImageView
        Glide.with(this)
                .load(bundle.getString("imagenKey"))
                .into(imagen);
    }
}

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

        ArrayList<Personaje> personaje = (ArrayList<Personaje>) bundle.get("Personaje");
        nombre = (TextView) findViewById(R.id.nombre);
        descripcion = (TextView) findViewById(R.id.descripcion);
        imagen = (ImageView) findViewById(R.id.imagenP);


        Personaje unico = new Personaje(personaje.get(0).getName(),personaje.get(0).getDescription(),personaje.get(0).getPeliculas(),personaje.get(0).getImagen());

        nombre.setText(unico.getName());
        descripcion.setText(unico.getDescription());

        Glide.with(this)
                .load(unico.getImagen())
                .into(imagen);
    }
}

package com.example.luisangel.biorvel.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luisangel.biorvel.Adaptadores.PersonajeAdapter;
import com.example.luisangel.biorvel.ListaMarvel.ListaCreadaCero;
import com.example.luisangel.biorvel.ListaMarvel.ListaDescripcion;
import com.example.luisangel.biorvel.ListaMarvel.ListaSinDescripcion;
import com.example.luisangel.biorvel.MarvelAPI.Constants;
import com.example.luisangel.biorvel.MarvelAPI.RestApiAdapter;
import com.example.luisangel.biorvel.MarvelAPI.Service;
import com.example.luisangel.biorvel.Personaje;
import com.example.luisangel.biorvel.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeroesFragment extends Fragment {
    RecyclerView recyclerPersonajes;
    ArrayList<Personaje> listaPersonajes;
    ListaDescripcion ld;

    public HeroesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_heroes, container, false);
        listaPersonajes = new ArrayList<>();
        listaPersonajes.add(new Personaje("Dinosaurio sin mandíbula",
                "Sobreviviente de la extincion al costo de su mandibula",
                "Extincion",""));
        listaPersonajes.add(new Personaje("Dinosaurio sin mandíbula",
                "Sobreviviente de la extincion al costo de su mandibula",
                "Extincion",""));
        listaPersonajes.add(new Personaje("Dinosaurio sin mandíbula",
                "Sobreviviente de la extincion al costo de su mandibula",
                "Extincion",""));

        recyclerPersonajes = (RecyclerView) vista.findViewById(R.id.recyclerId1);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getContext()));

        //listaPersonajes = llenarLista();

        PersonajeAdapter adapter = new PersonajeAdapter(listaPersonajes);
        recyclerPersonajes.setAdapter(adapter);

        return vista;
    }

    public ArrayList<Personaje> llenarLista(){
        final ArrayList<Personaje> regreso = new ArrayList<>();
        ld = new ListaDescripcion();
        ArrayList<String> busqueda = ld.rellenaNombresHeroes();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getPersonajeService();
        Call<JsonObject> call;

        for(int i = 0; i<busqueda.size();++i){
            call = service.getDataPersonaje(busqueda.get(i),Constants.APIKEY,Constants.TS,Constants.HASH);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        if(response.isSuccessful()){
                            String peliculas = "";
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            JSONObject personaje = jsonArray.getJSONObject(0);

                            String nombre = personaje.getString("name");
                            String descripcion = personaje.getString("description");

                            JSONObject image = personaje.getJSONObject("thumbnail");

                            String imagenPersonaje = image.getString("path") + "." + image.getString("extension");
                            regreso.add(new Personaje(nombre,peliculas,descripcion,imagenPersonaje));
                        }
                    }catch (JSONException e1){
                        e1.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) { }
            });
        }
        return regreso;
    }

}

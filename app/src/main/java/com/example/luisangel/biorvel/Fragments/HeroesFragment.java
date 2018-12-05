package com.example.luisangel.biorvel.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.luisangel.biorvel.Adaptadores.PersonajeAdapter;
import com.example.luisangel.biorvel.Adaptadores.RecyclerViewOnItemClickListener;
import com.example.luisangel.biorvel.ListaMarvel.ListaCreadaCero;
import com.example.luisangel.biorvel.ListaMarvel.ListaDescripcion;
import com.example.luisangel.biorvel.ListaMarvel.ListaSinDescripcion;
import com.example.luisangel.biorvel.MarvelAPI.Constants;
import com.example.luisangel.biorvel.MarvelAPI.RestApiAdapter;
import com.example.luisangel.biorvel.MarvelAPI.Service;
import com.example.luisangel.biorvel.Personaje;
import com.example.luisangel.biorvel.PersonajeActivity;
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

        recyclerPersonajes = (RecyclerView) vista.findViewById(R.id.recyclerId1);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getContext()));

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
                            //Toast.makeText(getActivity(), "in response", Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            JSONObject personaje = jsonArray.getJSONObject(0);


                            String nombre = personaje.getString("name") + "/" + personaje.getString("description");
                            String[] split = nombre.split("/");
                            String descripcion = "";

                            if(split.length == 1){
                                descripcion = "error";
                            }else{
                                descripcion = split[1];
                            }

                            nombre = split[0];


                            JSONObject image = personaje.getJSONObject("thumbnail");

                            String imagenPersonaje = image.getString("path") + "." + image.getString("extension");

                            listaPersonajes.add(new Personaje(nombre,peliculas,descripcion,imagenPersonaje));
                            PersonajeAdapter adapter;
                            recyclerPersonajes.setAdapter(adapter = new PersonajeAdapter(getActivity(),listaPersonajes,new RecyclerViewOnItemClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    //Toast.makeText(getActivity(),listaPersonajes.get(position).getDescription(),Toast.LENGTH_SHORT).show();
                                    String nombreKey = listaPersonajes.get(position).getName();
                                    String descripcionKey = listaPersonajes.get(position).getDescription();
                                    //String peliculasKey = listaPersonajes.get(view.getId()).getPeliculas();
                                    String imagenKey = listaPersonajes.get(position).getImagen();
                                    Intent intent = new Intent(getActivity(),PersonajeActivity.class);

                                    if(descripcionKey == ""){
                                        intent.putExtra("descripcionKey","NO se que pasa");
                                    }else{
                                        intent.putExtra("descripcionKey",descripcionKey);
                                    }

                                    //Pasando los datos
                                    intent.putExtra("nombreKey", nombreKey);

                                    //intent.putExtra("peliculasKey",peliculasKey);
                                    intent.putExtra("imagenKey",imagenKey);
                                    startActivity(intent);
                                }
                            }));
                        }
                    }catch (JSONException e1){
                        e1.printStackTrace();
                        Toast.makeText(getActivity(),"falla",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) { }
            });
        }





        return vista;
    }
}

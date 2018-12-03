package com.example.luisangel.biorvel.ListaMarvel;

import java.util.ArrayList;

public class ListaDescripcion {
    public ArrayList<String> rellenaNombresHeroes(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Iron Man");
        lista.add("Hulk");
        lista.add("Thor");
        lista.add("Sif");
        lista.add("Captain America");
        lista.add("Falcon");
        lista.add("Rocket Raccoon");
        lista.add("Vision");
        lista.add("Spider-Man");
        lista.add("Wong");
        lista.add("Wasp");
        return lista;
    }

    public ArrayList<String> rellenaNombresVillanos(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Abomination (Emil Blonsky)");
        lista.add("Leader");
        lista.add("Justin Hammer");
        lista.add("Whiplash (Mark Scarlotti)");
        lista.add("Arnin Zola");
        lista.add("Thanos");
        lista.add("Crossbones");
        lista.add("Ultron");
        lista.add("Vulture (Adrian Toomes)");
        lista.add("Shocker (Herman Schultz)");
        lista.add("Tinkerer");
        return lista;
    }
}

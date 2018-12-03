package com.example.luisangel.biorvel;

public class Personaje {
    private String name;
    private String description;
    private String peliculas;
    private String imagen;

    public Personaje(String name, String description, String peliculas, String imagen) {
        this.name = name;
        this.description = description;
        this.peliculas = peliculas;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(String peliculas) {
        this.peliculas = peliculas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

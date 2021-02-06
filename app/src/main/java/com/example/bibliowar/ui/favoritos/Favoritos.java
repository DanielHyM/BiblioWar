package com.example.bibliowar.ui.favoritos;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.bibliowar.ui.lugares.Lugar;
import com.example.bibliowar.ui.personajes.Persona;

import java.util.ArrayList;
import java.util.List;

public class Favoritos implements Parcelable {


    private String user;
    private ArrayList<Persona> listaPersonas;
    private ArrayList<Lugar> listaLugares;


    public Favoritos() {
        this.listaLugares = new ArrayList<>();
        this.listaPersonas = new ArrayList<>();
    }

    protected Favoritos(Parcel in) {
        user = in.readString();
        listaPersonas = in.createTypedArrayList(Persona.CREATOR);
        listaLugares = in.createTypedArrayList(Lugar.CREATOR);
    }

    public static final Creator<Favoritos> CREATOR = new Creator<Favoritos>() {
        @Override
        public Favoritos createFromParcel(Parcel in) {
            return new Favoritos(in);
        }

        @Override
        public Favoritos[] newArray(int size) {
            return new Favoritos[size];
        }
    };

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(ArrayList<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    @Override
    public String toString() {
        return "Favoritos{" +
                "user='" + user + '\'' +
                ", listaPersonas=" + listaPersonas +
                ", listaLugares=" + listaLugares +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user);
        dest.writeTypedList(listaPersonas);
        dest.writeTypedList(listaLugares);
    }
}

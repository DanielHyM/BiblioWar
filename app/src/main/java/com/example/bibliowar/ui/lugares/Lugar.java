package com.example.bibliowar.ui.lugares;

import android.os.Parcel;
import android.os.Parcelable;

public class Lugar implements Parcelable {

    private String nombre;
    private String pais;
    private String continente;
    private String info;
    private String foto;

    public Lugar() {
    }

    public Lugar(String nombre, String pais, String continente, String info, String foto) {
        this.nombre = nombre;
        this.pais = pais;
        this.continente = continente;
        this.info = info;
        this.foto = foto;
    }

    protected Lugar(Parcel in) {
        nombre = in.readString();
        pais = in.readString();
        continente = in.readString();
        info = in.readString();
        foto = in.readString();
    }

    public static final Creator<Lugar> CREATOR = new Creator<Lugar>() {
        @Override
        public Lugar createFromParcel(Parcel in) {
            return new Lugar(in);
        }

        @Override
        public Lugar[] newArray(int size) {
            return new Lugar[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", continente='" + continente + '\'' +
                ", info='" + info + '\'' +
                ", foto=" + foto +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(pais);
        dest.writeString(continente);
        dest.writeString(info);
        dest.writeString(foto);
    }
}

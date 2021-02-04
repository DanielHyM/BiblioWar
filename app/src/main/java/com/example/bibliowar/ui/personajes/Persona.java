package com.example.bibliowar.ui.personajes;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {

    private String nombre;
    private String apellido;
    private String fechaNac;
    private String foto;
    private String info;


    public Persona() {
    }

    public Persona(String nombre, String apellido, String fechaNac, String foto, String info) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.foto = foto;
        this.info = info;
    }

    protected Persona(Parcel in) {
        nombre = in.readString();
        apellido = in.readString();
        fechaNac = in.readString();
        foto = in.readString();
        info = in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", foto=" + foto +
                ", info='" + info + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeString(fechaNac);
        dest.writeString(foto);
        dest.writeString(info);
    }
}

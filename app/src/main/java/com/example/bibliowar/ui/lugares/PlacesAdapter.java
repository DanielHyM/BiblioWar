package com.example.bibliowar.ui.lugares;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bibliowar.MainActivity;
import com.example.bibliowar.R;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private ArrayList<Lugar> listaLugaresAdapter;
    private Context context;
    private Fragment frag;


    public PlacesAdapter(ArrayList<Lugar> listaLugaresAdapter, Context c, Fragment fragment) {
        this.listaLugaresAdapter = listaLugaresAdapter;
        this.context = c;
        this.frag = fragment;
    }

    public PlacesAdapter(ArrayList<Lugar> listaLugaresAdapter, Context context) {
        this.listaLugaresAdapter = listaLugaresAdapter;
        this.context = context;
    }

    @NonNull
    @Override
    public PlacesAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.PlaceViewHolder holder, int position) {

        Lugar lugar = listaLugaresAdapter.get(position);
        Glide.with(context).load(lugar.getFoto()).into(holder.imgPlace);
        holder.placeNombre.setText(lugar.getNombre());
        holder.pais.setText(lugar.getPais());

    }

    @Override
    public int getItemCount() {
        return listaLugaresAdapter.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        ImageView imgStar;
        ImageView imgPlace;
        TextView placeNombre;
        TextView pais;
        Button button_detail;


        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPlace = itemView.findViewById(R.id.cardview_image_place);
            placeNombre = itemView.findViewById(R.id.cv_textview_name_place);
            pais = itemView.findViewById(R.id.cv_textView_name_pais);
            button_detail = itemView.findViewById(R.id.cv_button_place_detail);
            imgStar = itemView.findViewById(R.id.img_lugar_star);


            button_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetailPlacesActivity.class);
                    Lugar lug = listaLugaresAdapter.get(getAdapterPosition());
                    i.putExtra("lugar", (Parcelable) lug);
                    v.getContext().startActivity(i);

                }
            });

            imgStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Lugar l = listaLugaresAdapter.get(getAdapterPosition());
                    ((MainActivity)frag.getActivity()).listaLugares.add(l);
                    Toast.makeText(v.getContext(),"Lugar añadido a la lista", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}

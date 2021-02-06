package com.example.bibliowar.ui.personajes;

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

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<Persona> listaPersonasAdapter;
    private Context context;
    private Fragment frag;

    public CharacterAdapter(ArrayList<Persona> listaPersonasAdapter, Context c, Fragment fragment) {
        this.listaPersonasAdapter = listaPersonasAdapter;
        this.context = c;
        this.frag = fragment;

    }

    public CharacterAdapter(ArrayList<Persona> listaPersonasAdapter, Context context) {
        this.listaPersonasAdapter = listaPersonasAdapter;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {

        Persona p = listaPersonasAdapter.get(position);
        Glide.with(context).load(p.getFoto()).into(holder.imgPersona);
        holder.tvNombre.setText(p.getNombre());
        holder.tvApellido.setText(p.getApellido());
        holder.tvFechaNac.setText(p.getFechaNac());
    }

    @Override
    public int getItemCount() {
        return listaPersonasAdapter.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgStar;
        ImageView imgPersona;
        TextView tvNombre;
        TextView tvApellido;
        TextView tvFechaNac;
        Button buttonDetail;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);


            imgPersona = itemView.findViewById(R.id.image_cardView_character);
            tvNombre = itemView.findViewById(R.id.tv_card_character_nombre);
            tvApellido = itemView.findViewById(R.id.tv_card_character_apellido);
            tvFechaNac = itemView.findViewById(R.id.tv_card_character_fechaNac);
            buttonDetail = itemView.findViewById(R.id.button_card_character_details);
            imgStar = itemView.findViewById(R.id.img_persona_star);


            buttonDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetailCharacterActivity.class);
                    Persona p = listaPersonasAdapter.get(getAdapterPosition());
                    i.putExtra("persona", (Parcelable) p);
                    v.getContext().startActivity(i);
                }
            });

            imgStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Persona p = listaPersonasAdapter.get(getAdapterPosition());
                    ((MainActivity)frag.getActivity()).listaPersonas.add(p);
                    Toast.makeText(v.getContext(),"Personaje añadido a la lista", Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}

package com.example.bibliowar.ui.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliowar.R;
import com.example.bibliowar.ui.lugares.Lugar;
import com.example.bibliowar.ui.personajes.Persona;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatePlaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePlaceFragment extends Fragment {

    EditText nombre;
    EditText pais;
    EditText continente;
    Button button_add_place;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreatePlaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatePlaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePlaceFragment newInstance(String param1, String param2) {
        CreatePlaceFragment fragment = new CreatePlaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_place, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombre = view.findViewById(R.id.admin_place_nombre);
        pais = view.findViewById(R.id.admin_place_pais);
        continente = view.findViewById(R.id.admin_place_continente);
        button_add_place  = view.findViewById(R.id.button_admin_place);





        button_add_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lugar l = new Lugar(nombre.getText().toString(),pais.getText().toString(),continente.getText().toString(),"https://es.wikipedia.org/wiki/Wikipedia:Portada",
                        "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/lugares%2Fplanet.png?alt=media&token=a519dd53-999b-440a-83c9-e24f7289dec3");

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseStorage storage = FirebaseStorage.getInstance();

                db.collection("lugares")
                        .add(l)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("persona", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(view.getContext(),"LUGAR AÑADIDO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("persona", "Error adding document", e);
                                Toast.makeText(view.getContext(),"ERROR AL AÑADIR EL LUGAR",Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

    }
}
package com.example.bibliowar.ui.personajes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bibliowar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterFragment extends Fragment {

    private StorageReference storageRef;
    private RecyclerView rvPersona;
    private GridLayoutManager glm;
    private CharacterAdapter adapter;
    ArrayList<Persona> listaPersonas = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CharacterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharacterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterFragment newInstance(String param1, String param2) {
        CharacterFragment fragment = new CharacterFragment();
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
      View  view =  inflater.inflate(R.layout.fragment_character, container, false);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();




        //loadDataOnCloudFirestore();


        db.collection("personas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("personaInsert", document.getId() + " => " + document.getData());
                                listaPersonas.add(new Persona(document.get("nombre").toString(),document.get("apellido").toString(),
                                        document.get("fechaNac").toString(),document.get("foto").toString(),document.get("info").toString()));
                            }
                        } else {
                            Log.w("personaInsert", "Error getting documents.", task.getException());
                        }

                        rvPersona = view.findViewById(R.id.rv_characters);
                        glm = new GridLayoutManager(view.getContext(),1);
                        rvPersona.setLayoutManager(glm);
                        adapter = new CharacterAdapter(listaPersonas, view.getContext());
                        rvPersona.setAdapter(adapter);

                    }
                });




    }


    public void loadDataOnCloudFirestore(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listaPersonas.add(new Persona("Adolf","Hitler","20-04-1889",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/personas%2Fhitler.jpg?alt=media&token=75461e81-5cee-4e97-9022-3a33834b377e","https://es.wikipedia.org/wiki/Adolf_Hitler"));
        listaPersonas.add(new Persona("Winston","Churchill","30-11-1874",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/personas%2Fchur.jpg?alt=media&token=892650c3-02ed-41e8-add7-efed70d000c0","https://es.wikipedia.org/wiki/Winston_Churchill"));
        listaPersonas.add(new Persona("Iósif","Stalin","18-12-1878",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/personas%2Fstalin.jpg?alt=media&token=37c49dc7-a2af-497a-98cd-d56f77b04b40","https://es.wikipedia.org/wiki/I%C3%B3sif_Stalin"));
        listaPersonas.add(new Persona("Benito","Mussolini","29-07-1883",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/personas%2Fmussolini.jpg?alt=media&token=244fe646-df36-4b7e-be21-880930eedd4d","https://es.wikipedia.org/wiki/Benito_Mussolini"));
        listaPersonas.add(new Persona("Franklin D.","Roosevelt","30-01-1882",
                "https://firebasestorage.googleapis.com/v0/b/bibliowar-dffb0.appspot.com/o/personas%2Froosvelt.jpg?alt=media&token=3cb333c8-1d32-4a11-8a3a-d2b64ce465ee","https://es.wikipedia.org/wiki/Franklin_D._Roosevelt"));

       for(int f = 0; f < listaPersonas.size(); f++){

           db.collection("personas")
                   .add(listaPersonas.get(f))
                   .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                       @Override
                       public void onSuccess(DocumentReference documentReference) {
                           Log.d("persona", "DocumentSnapshot added with ID: " + documentReference.getId());
                       }
                   })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Log.w("persona", "Error adding document", e);
                       }
                   });

       }


    }
}
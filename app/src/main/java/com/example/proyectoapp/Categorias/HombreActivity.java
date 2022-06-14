package com.example.proyectoapp.Categorias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectoapp.Adapter.ClothingmenAdapter;
import com.example.proyectoapp.Domain.ClothingMenDomain;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.proyectoapp.R;

public class HombreActivity extends AppCompatActivity {

    LinearLayoutManager mlinearLayoutManager;
    GridLayoutManager mgridLayoutManager;
    RecyclerView mRecyclerViewMen;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<ClothingMenDomain, ClothingmenAdapter> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<ClothingMenDomain> options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hombre);


        //mgridLayoutManager = new GridLayoutManager(this,2);
        //mgridLayoutManager.setReverseLayout(true);
        //mgridLayoutManager.setStackFromEnd(true);

        //mlinearLayoutManager = new LinearLayoutManager(this, mlinearLayoutManager.VERTICAL,false);
        mgridLayoutManager = new GridLayoutManager(this,2);
        //mlinearLayoutManager.setReverseLayout(true);
        //mlinearLayoutManager.setStackFromEnd(true);

        mRecyclerViewMen = findViewById(R.id.recyclerViewMen);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Ropahombres");

        showData();

    }

    private void showData(){

        options = new FirebaseRecyclerOptions.Builder<ClothingMenDomain>().setQuery(mDatabaseReference,ClothingMenDomain.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ClothingMenDomain, ClothingmenAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClothingmenAdapter holder, int position, @NonNull ClothingMenDomain model) {

                holder.setDetails(getApplicationContext(),model.getClothingmen(),model.getDescripcion(), model.getMarca(), model.getPrecio());
            }

            @NonNull
            @Override
            public ClothingmenAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_men,parent,false);
                ClothingmenAdapter clothingmenAdapter = new ClothingmenAdapter(itemView);
                clothingmenAdapter.setOnClickListener(new ClothingmenAdapter.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(HombreActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                        //Con el siguiente swich cambiaremos de activity según la posición de los items del recyclerView
                        switch (position){
                            case 0:{
                                Intent intent = new Intent(HombreActivity.this,MujerActivity.class);
                                startActivity(intent);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(HombreActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                    }
                });


                return clothingmenAdapter;
            }
        };



        mRecyclerViewMen.setLayoutManager(mgridLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerViewMen.setAdapter(firebaseRecyclerAdapter);

    }

    protected void onStart(){
        super.onStart();
        if (firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }


}
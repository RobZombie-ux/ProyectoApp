package com.example.proyectoapp.Categorias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectoapp.Adapter.ClothingmenAdapter;
import com.example.proyectoapp.Adapter.ClothingwomanAdapter;
import com.example.proyectoapp.Domain.ClothingMenDomain;
import com.example.proyectoapp.Domain.ClothingWomanDomain;
import com.example.proyectoapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MujerActivity extends AppCompatActivity {

    GridLayoutManager mgridLayoutManager;
    RecyclerView mRecyclerViewWoman;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<ClothingWomanDomain, ClothingwomanAdapter> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<ClothingWomanDomain> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mujer);

        mgridLayoutManager = new GridLayoutManager(this,2);

        mRecyclerViewWoman = findViewById(R.id.recyclerViewWoman);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Ropamujeres");

        showData();
    }

    private void showData(){

        options = new FirebaseRecyclerOptions.Builder<ClothingWomanDomain>().setQuery(mDatabaseReference,ClothingWomanDomain.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ClothingWomanDomain, ClothingwomanAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClothingwomanAdapter holder, int position, @NonNull ClothingWomanDomain model) {

                holder.setDetails(getApplicationContext(),model.getClothingwoman(),model.getDescripcion(), model.getMarca(), model.getPrecio());
            }

            @NonNull
            @Override
            public ClothingwomanAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_woman,parent,false);
                ClothingwomanAdapter clothingwomanAdapter = new ClothingwomanAdapter(itemView);
                clothingwomanAdapter.setOnClickListener(new ClothingwomanAdapter.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(MujerActivity.this, "Hola mundo", Toast.LENGTH_SHORT).show();

                    }
                });

                return clothingwomanAdapter;
            }
        };

        mRecyclerViewWoman.setLayoutManager(mgridLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerViewWoman.setAdapter(firebaseRecyclerAdapter);

    }

    protected void onStart(){
        super.onStart();
        if (firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }

}
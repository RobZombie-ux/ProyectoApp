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

import com.example.proyectoapp.Adapter.ClothingirlAdapter;
import com.example.proyectoapp.Adapter.ClothingmenAdapter;
import com.example.proyectoapp.Domain.ClothingGirlDomain;
import com.example.proyectoapp.Domain.ClothingMenDomain;
import com.example.proyectoapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NinaActivity extends AppCompatActivity {

    GridLayoutManager mgridLayoutManager;
    RecyclerView mRecyclerViewGirl;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<ClothingGirlDomain, ClothingirlAdapter> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<ClothingGirlDomain> options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nina);

        mgridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerViewGirl = findViewById(R.id.recyclerViewGirl);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Ropaniñas");

        showData();

    }

    private void showData(){

        options = new FirebaseRecyclerOptions.Builder<ClothingGirlDomain>().setQuery(mDatabaseReference,ClothingGirlDomain.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ClothingGirlDomain, ClothingirlAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClothingirlAdapter holder, int position, @NonNull ClothingGirlDomain model) {

                holder.setDetails(getApplicationContext(),model.getClothingirl(),model.getDescripcion(), model.getMarca(), model.getPrecio());
            }

            @NonNull
            @Override
            public ClothingirlAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_girl,parent,false);
                ClothingirlAdapter clothingirlAdapter = new ClothingirlAdapter(itemView);
                clothingirlAdapter.setOnClickListener(new ClothingirlAdapter.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(NinaActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                    }
                });

                return clothingirlAdapter;
            }
        };

        mRecyclerViewGirl.setLayoutManager(mgridLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerViewGirl.setAdapter(firebaseRecyclerAdapter);

    }

    protected void onStart(){
        super.onStart();
        if (firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }

}
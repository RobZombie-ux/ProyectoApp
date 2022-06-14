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

import com.example.proyectoapp.Adapter.ClothingboyAdapter;
import com.example.proyectoapp.Adapter.ClothingwomanAdapter;
import com.example.proyectoapp.Domain.ClothingBoyDomain;
import com.example.proyectoapp.Domain.ClothingWomanDomain;
import com.example.proyectoapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NinoActivity extends AppCompatActivity {

    GridLayoutManager mgridLayoutManager;
    RecyclerView mRecyclerViewBoy;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<ClothingBoyDomain, ClothingboyAdapter> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<ClothingBoyDomain> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nino);

        mgridLayoutManager = new GridLayoutManager(this,2);

        mRecyclerViewBoy = findViewById(R.id.recyclerViewBoy);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Ropaniños");

        showData();
    }

    public void showData(){

        options = new FirebaseRecyclerOptions.Builder<ClothingBoyDomain>().setQuery(mDatabaseReference,ClothingBoyDomain.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ClothingBoyDomain, ClothingboyAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClothingboyAdapter holder, int position, @NonNull ClothingBoyDomain model) {

                holder.setDetails(getApplicationContext(),model.getClothingboy(),model.getDescripcion(), model.getMarca(), model.getPrecio());
            }

            @NonNull
            @Override
            public ClothingboyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_boy,parent,false);
                ClothingboyAdapter clothingboyAdapter = new ClothingboyAdapter(itemView);
                clothingboyAdapter.setOnClickListener(new ClothingboyAdapter.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(NinoActivity.this, "Hola mundo", Toast.LENGTH_SHORT).show();
                    }
                });
                return clothingboyAdapter;
            }
        };

        mRecyclerViewBoy.setLayoutManager(mgridLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerViewBoy.setAdapter(firebaseRecyclerAdapter);

    }

    protected void onStart(){
        super.onStart();
        if (firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }

}
package com.example.proyectoapp.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoapp.R;
import com.squareup.picasso.Picasso;

public class ClothingboyAdapter extends RecyclerView.ViewHolder {

    View mview;

    public ClothingboyAdapter(@NonNull View itemView) {
        super(itemView);

        mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }

    public void setDetails(Context ctx, String clothingboy, String descripcion, String marca, String precio){

        ImageView mFoto = mview.findViewById(R.id.clothingPic);
        TextView mDescripcion = mview.findViewById(R.id.clothingDescripcion);
        TextView mMarca = mview.findViewById(R.id.clothingMarca);
        TextView mPrecio = mview.findViewById(R.id.clothingPrecio);

        mDescripcion.setText(descripcion);
        mMarca.setText(marca);
        mPrecio.setText(precio);

        Picasso.get().load(clothingboy).into(mFoto);
    }

    private ClothingboyAdapter.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ClothingboyAdapter.ClickListener clickListener){
        mClickListener = clickListener;
    }

}

package com.example.proyectoapp.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ClothingmenAdapter extends RecyclerView.ViewHolder {

    View mview;

    public ClothingmenAdapter(@NonNull View itemView) {
        super(itemView);

        mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    public void setDetails(Context ctx, String clothingmen, String descripcion, String marca, String precio){

        ImageView mFoto = mview.findViewById(R.id.clothingPic);
        TextView mDescripcion = mview.findViewById(R.id.clothingDescripcion);
        TextView mMarca = mview.findViewById(R.id.clothingMarca);
        TextView mPrecio = mview.findViewById(R.id.clothingPrecio);

        mDescripcion.setText(descripcion);
        mMarca.setText(marca);
        mPrecio.setText(precio);

        Picasso.get().load(clothingmen).into(mFoto);
    }

    private ClothingmenAdapter.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ClothingmenAdapter.ClickListener clickListener){
        mClickListener = clickListener;
    }




}

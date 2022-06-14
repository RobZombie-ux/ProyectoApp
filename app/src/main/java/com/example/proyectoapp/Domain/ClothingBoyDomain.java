package com.example.proyectoapp.Domain;

public class ClothingBoyDomain {

    String clothingboy;
    String descripcion;
    String marca;
    String precio;
    
    public ClothingBoyDomain() {
        
    }


    public String getClothingboy() {
        return this.clothingboy;
    }

    public void setClothingboy(String clothingboy) {
        this.clothingboy = clothingboy;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public  String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public  String getPrecio(){
        return this.precio;
    }

    public void setPrecio(String precio){
        this.precio = precio;
    }
    
    
}

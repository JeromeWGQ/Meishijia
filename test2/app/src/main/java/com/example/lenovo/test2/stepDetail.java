package com.example.lenovo.test2;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/7/14.
 */
public class stepDetail {
    String image;
    String description;
    ArrayList<material> materials = new ArrayList<material>();

    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return image;
    }
    public void setDescription(String Desc){
        this.description = Desc;
    }
    public String getDescription(){
        return description;
    }

    public ArrayList<material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<material> materials) {
        this.materials = materials;
    }
}

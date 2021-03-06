package com.example.room.Model;

import java.io.Serializable;

public class PopularAreasModel implements Serializable {
    private int imgId;
    private String description;

    public PopularAreasModel(int imgId, String description) {
        this.imgId = imgId;
        this.description = description;
    }

    public PopularAreasModel() {
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

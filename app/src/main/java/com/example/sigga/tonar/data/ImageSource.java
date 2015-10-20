package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 19.10.2015.
 */
public class ImageSource implements JSONPopulator {
    private String imageSource;

    public String getImageSource() {
        return imageSource;
    }

    @Override
    public void populate(JSONObject data) {

        imageSource = data.optString("imageSource");

    }
}

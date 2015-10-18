package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 8.10.2015.
 */
public class Name implements JSONPopulator  {

    private String name;


    public String getName() {
        return name;
    }

    @Override

    public void populate(JSONObject data) {

        name = data.optString("name");

    }

}

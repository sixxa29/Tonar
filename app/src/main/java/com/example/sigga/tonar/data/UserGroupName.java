package com.example.sigga.tonar.data;

import org.json.JSONObject;

/**
 * Created by sigga on 19.10.2015.
 */
public class UserGroupName implements JSONPopulator {
    private String userGroupName;

    public String getUserGroupName() {
        return userGroupName;
    }

    @Override
    public void populate(JSONObject data) {

        userGroupName = data.optString("dateOfShow");

    }
}

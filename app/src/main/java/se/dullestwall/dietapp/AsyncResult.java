package se.dullestwall.dietapp;

import org.json.JSONObject;

/**
 * Created by Patrik on 2015-02-24.
 */
interface AsyncResult
{
    void onResult(JSONObject object);
}
package com.example.quickstart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by praveen on 1/23/17.
 */
public class JSONWeatherParser {

    public static Weather getWeather(String data) throws JSONException {

        Weather weather = new Weather();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // We start extracting the info

        JSONObject coordObj = getObject("coord", jObj);
        weather.setLatitude(getFloat("lat", coordObj));
        weather.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        weather.setCountry(getString("country", sysObj));
        weather.setSunrise(getInt("sunrise", sysObj));
        weather.setSunset(getInt("sunset", sysObj));

        weather.setCity(getString("name", jObj));
        weather.setTemprature(getFloat("temprature", jObj));
        weather.setHumidity(getFloat("humidity", jObj));
        weather.setPressure(getFloat("pressure", jObj));

        // We download the icon to show


        return weather;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}

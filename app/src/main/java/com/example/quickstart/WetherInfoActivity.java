package com.example.quickstart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

/**
 * Created by praveen on 1/23/17.
 */
public class WetherInfoActivity extends AppCompatActivity {

    TextView textTemp, humidity, pressure, longi, latit, sunrise, sunset;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wether);


        textTemp = (TextView) findViewById(R.id.temprature);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressur);
        longi = (TextView) findViewById(R.id.longi);
        latit = (TextView) findViewById(R.id.latit);
        sunrise = (TextView) findViewById(R.id.sunrise);
        sunset = (TextView) findViewById(R.id.sunset);
        imgView = (ImageView) findViewById(R.id.condIcon);

        String city = "London,UK";
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});

    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();

            String data = (new WeatherHttpClient()).getWeatherData(params[0]);
            try {
                weather = JSONWeatherParser.getWeather(data);
                weather.iconData = ((new WeatherHttpClient()).getImage(weather.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            // cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());

            textTemp.setText("" + Math.round((weather.getTemprature() - 273.15)) + "�C");
            humidity.setText("" + weather.getHumidity() + "%");
            pressure.setText("" + weather.getPressure() + " hPa");
            longi.setText("" + weather.getLongitude() + "longi");
            latit.setText("" + weather.getLatitude() + "latitude");
            sunrise.setText("" + weather.getSunrise() + "srise");
            sunset.setText("" + weather.getSunset() + "sset");

        }
    }
}
package com.example.farmersagriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

public class Sensor extends AppCompatActivity {
    private Button btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        btns = findViewById(R.id.btna);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
       // final Sensor proximitysensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //SensorEventListener sensorEventListener = new SensorEventListener() {
          //  @Override
           // public void onSensorChanged(SensorEvent event) {
              //  if (event.values[0] < proximitysensor.getMaximumRange()) {
             //       getWindow().getDecorView().setBackgroundColor(Color.RED);
               // } else {
                 //   getWindow().getDecorView().setBackgroundColor(Color.GREEN);

                //}

            //}

            //@Override
          //  public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            //}
        //};
/*
        sensorManager.registerListener(sensorEventListener, proximitysensor, 2 * 1000 * 1000);
*/
    }
}

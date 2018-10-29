package android.base.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensorTemp;
    private Sensor sensorPressure;
    private Sensor sensorHumidity;
    TextView tvTemperature;
    TextView tvPressure;
    TextView tvHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPressure = findViewById(R.id.tv_pressure);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvHumidity = findViewById(R.id.tv_humidity);

        // Менеджер датчиков
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY); // TYPE_ABSOLUTE_HUMIDITY not found
        // Регистрируем слушателя датчиков
        if(!sensorManager.registerListener(listenerTemp, sensorTemp, SensorManager.SENSOR_DELAY_NORMAL))
            tvTemperature.setText("No Temperature Sensor found");
        if(!sensorManager.registerListener(listenerPressure, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL))
            tvPressure.setText("No Pressure Sensor found");
        if(!sensorManager.registerListener(listenerHumidity, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL))
            tvHumidity.setText("No Humidity Sensor found");
    }


    // Если приложение свернуто, то не будем тратить энергию на получение информации по датчикам
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerPressure, sensorPressure);
        sensorManager.unregisterListener(listenerHumidity, sensorHumidity);
        sensorManager.unregisterListener(listenerTemp, sensorTemp);
    }

    // Слушатель датчика температуры
    private final SensorEventListener listenerTemp = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
              String str = String.valueOf(event.values[0]);
              tvTemperature.setText("Temperature is: " + str);
        }
    };

    // Слушатель датчика давления
    private final SensorEventListener listenerPressure = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
              String str = String.valueOf(event.values[0]);
              tvPressure.setText("Pressure is: " + str);
        }
    };

    // Слушатель датчика влажности
    private final SensorEventListener listenerHumidity = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
              String str = String.valueOf(event.values[0]);
              tvHumidity.setText("Humidity is: " + str);
        }
    };
}

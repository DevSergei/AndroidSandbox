package android.base.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartService = findViewById(R.id.buttonStartService);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyWeatherIntentService.class);

                intent.setAction(ACTION_TEMP);
                intent.putExtra(EXTRA_CITY_KEY,EXTRA_DFLT_CITY);
                startService(intent);
            }
        });
    }
}

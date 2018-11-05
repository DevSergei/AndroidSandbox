package android.base.saving_settings;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String KEY = "mykey";

    private EditText etIo;
    private Button btnSave;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIo = findViewById(R.id.et_io);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                savePreferences(sharedPreferences);
            }
        });

        btnLoad = findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                loadPreferences(sharedPreferences);
            }
        });
    }

    private void savePreferences(SharedPreferences sharedPref){
        String value = etIo.getText().toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY, value).apply();
    }

    private void loadPreferences(SharedPreferences sharedPref){
        String value = sharedPref.getString(KEY, "default value");
        etIo.setText(value);
    }
}

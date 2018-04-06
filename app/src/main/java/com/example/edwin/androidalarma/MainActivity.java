package com.example.edwin.androidalarma;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner minutos;
    Spinner horas;
    Button boton;
    int hora;
    int minuto;
    EditText mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje = findViewById(R.id.editText1);
        boton = findViewById(R.id.button);
        horas = findViewById(R.id.spinnerHoras);
        minutos = findViewById(R.id.spinnerMinutos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.horas,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horas.setAdapter(adapter);
        horas.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.minutos,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutos.setAdapter(adapter2);
        minutos.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, mensaje.getText().toString());
                intent.putExtra(AlarmClock.EXTRA_HOUR, hora);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minuto);

                //validacion
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = horas.getSelectedItem().toString();
        String text2 = minutos.getSelectedItem().toString();

        hora = Integer.parseInt(text);
        minuto = Integer.parseInt(text2);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

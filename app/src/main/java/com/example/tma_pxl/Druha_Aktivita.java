package com.example.tma_pxl;
/*Xaver Zak*/

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Druha_Aktivita extends AppCompatActivity {

    Button button_potvrdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druha_aktivita);

        EditText editText = (EditText) findViewById(R.id.editText_todo);

        button_potvrdit = findViewById(R.id.button_potvrdit);
        button_potvrdit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), com.example.tma_pxl.MainActivity.class);
            String data = editText.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("STRING_DATA",data);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        });

    }

}
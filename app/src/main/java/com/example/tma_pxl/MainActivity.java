package com.example.tma_pxl;
/*Xaver Zak*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> toDo_zoznam = new ArrayList<String>();
    ListView zoznam;
    ArrayAdapter<String> adapter;

    Button button_pridaj;
    Button button_odober;

    ActivityResultLauncher<Intent> activity_luncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK){
                    Intent intent_data = result.getData();
                    if(intent_data == null){return;}

                    Bundle bundle = intent_data.getExtras();
                    String string_data= bundle.getString("STRING_DATA");
                    adapter.add(string_data);
                }
            }
    );

    int selector = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView micro_text = findViewById((R.id.textView_todolist));
        zoznam = (ListView) findViewById(R.id.ListView_nvm);
        button_pridaj = findViewById(R.id.button_pridaj);
        button_odober = findViewById(R.id.button_odober);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, toDo_zoznam);
        zoznam.setAdapter(adapter);

        Intent intent = new Intent(MainActivity.this, Druha_Aktivita.class);

        button_pridaj.setOnClickListener(view -> activity_luncher.launch(intent));

        zoznam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*NEFUNGUJE TO
                zoznam.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                zoznam.setSelection(android.R.color.holo_green_light);*/
                selector = i;
                Toast.makeText(MainActivity.this, "vybrate_id: " + i,
                        Toast.LENGTH_SHORT).show();
            }
        });

        button_odober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selector < 0){
                    Toast.makeText(MainActivity.this, "Nemas nic oznacene! ",
                            Toast.LENGTH_SHORT).show();
                }
                if (selector > adapter.getCount() ){
                    Toast.makeText(MainActivity.this, "Chces odstanit mimo rozsah! ",
                            Toast.LENGTH_SHORT).show();
                }
                if (selector >= 0 && selector < adapter.getCount()){
                    adapter.remove(adapter.getItem(selector));
                    selector = -1;
                }

            }
        });


    }

}
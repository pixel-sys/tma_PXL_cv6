package com.example.tma_pxl;
/*Xaver Zak*/

import android.app.Notification;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> toDo_zoznam = new ArrayList<String>();
    ListView zoznam;
    ArrayAdapter<String> adapter;


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
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, toDo_zoznam);
        zoznam.setAdapter(adapter);

        Intent intent = new Intent(MainActivity.this, Druha_Aktivita.class);

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

        registerForContextMenu(zoznam);
    }

    public void onClick_odober(View view) {
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

/*    volanie metody na vytvorenie hlavneho menu*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.otvorit:
                Toast.makeText(MainActivity.this, "you pressed open" ,
                        Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ulozit:
                /*NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL)
                        *//*.setSmallIcon(R.drawable.notification_icon)*//*
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        *//*.setContentIntent(pendingIntent)*//*
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(5, builder.build());*/

                return true;

            case R.id.pridat:
                Intent intent = new Intent(MainActivity.this, Druha_Aktivita.class);
                activity_luncher.launch(intent);
                return true;

            case R.id.odobrat:
                onClick_odober(null);
                return true;

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.kontextove_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.odobrat:
                onClick_odober(null);
                return true;
            case R.id.ponechat:
                selector = -1;
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
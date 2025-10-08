package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String strCityToAdd = "";
    int selectedIndex;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //so that we can select items in our list
        selectedIndex = -1; //some impossible index to indicate nothing is selected
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIndex = i;

                //we also want to highlight the selected item so the user knows its selected

            }
        });

        //set up the buttons

        Button confirmButton = (Button) findViewById(R.id.confirmButton);

        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the add button");

                //show the dialogue
                EditText editText = (EditText) findViewById(R.id.edit_text_id);
                editText.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);

                //wait for user to press confirm of some kind
                //confirm button that adds str (does below) when pressed if str!=""



            }
        });

        //declared above
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the confirm button");
                EditText editText = (EditText) findViewById(R.id.edit_text_id);
                strCityToAdd = editText.getText().toString();

                if(!strCityToAdd.isEmpty())
                {
                    cityAdapter.add(strCityToAdd);
                    strCityToAdd = "";
                    editText.setText("");
                    editText.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                }


            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the delete button");

                if (selectedIndex != -1){
                    cityAdapter.remove(cityAdapter.getItem(selectedIndex));
                    selectedIndex=-1;
                }
            }
        });

    }
}
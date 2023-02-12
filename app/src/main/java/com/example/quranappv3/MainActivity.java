package com.example.quranappv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.quranappv3.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // views
    Spinner surahs, paras;
    RecyclerView recyclerView;

    // others
    Utils util = new Utils();
    List<Ayat> ayats;
    ArrayJSONQuranHelper jsonHelper;

    // adapters
    AyatRecyclerViewAdapter ayatRecyclerViewAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // new objects
        jsonHelper = new ArrayJSONQuranHelper("QuranMetaData.json", this);

        // getting ref
        surahs = findViewById(R.id.spinnerSurah);
        paras = findViewById(R.id.spinnerPara);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // getting data
        ayats = jsonHelper.getAllAyats();

        // making adapters
        ArrayAdapter suraAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, util.englishSurahNames);
        suraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter paraAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, util.englishParahName);
        paraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ayatRecyclerViewAdapter = new AyatRecyclerViewAdapter(this, ayats);

        // setting adapters and listeners
        surahs.setAdapter(suraAdapter);
        paras.setAdapter(paraAdapter);
        surahs.setOnItemSelectedListener(this);
        paras.setOnItemSelectedListener(this);
        recyclerView.setAdapter(ayatRecyclerViewAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == surahs.getId()) { // surah selected
            ayatRecyclerViewAdapter.setAyats(jsonHelper.getAyats(jsonHelper.getSurahStart(position + 1), jsonHelper.getSurahEnd(position + 1)));
        }
        else {
            ayatRecyclerViewAdapter.setAyats(jsonHelper.getAyats(jsonHelper.getParaStart(position + 1), jsonHelper.getParaEnd(position + 1)));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
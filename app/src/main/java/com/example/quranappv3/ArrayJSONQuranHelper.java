package com.example.quranappv3;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArrayJSONQuranHelper {

    String FileName;
    JSONArray arr;
    Context context;

    public ArrayJSONQuranHelper(String fileName, Context context) {
        FileName = fileName;
        this.context = context;
        try {
            arr = new JSONArray(getJsonFromAssets());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Ayat> getAyats(int from, int to) {
        List<Ayat> a = new ArrayList<>();
        for (int i = from; i <= to; i++)
        {
            try {
                a.add(new Ayat(arr.getJSONObject(i).getString("text"),
                        arr.getJSONObject(i).getString("UrduTranslation")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    public int getSurahStart(int surahNumber)
    {
        for (int i = 0; i < arr.length(); i++)
        {
            try {
                if (arr.getJSONObject(i).getInt("surah_number") == surahNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getSurahEnd(int surahNumber)
    {
        for (int i = arr.length() - 1; i >= 0; i--)
        {
            try {
                if (arr.getJSONObject(i).getInt("surah_number") == surahNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getParaStart(int ParaNumber)
    {
        for (int i = 0; i < arr.length(); i++)
        {
            try {
                if (arr.getJSONObject(i).getInt("juz") == ParaNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getParaEnd(int paraNumber)
    {
        for (int i = arr.length() - 1; i >= 0; i--)
        {
            try {
                if (arr.getJSONObject(i).getInt("juz") == paraNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public List<Ayat> getAllAyats() {
        List<Ayat> a = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++)
        {
            try {
                a.add(new Ayat(arr.getJSONObject(i).getString("text"),
                        arr.getJSONObject(i).getString("UrduTranslation")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    public JSONArray getArray() {
        return arr;
    }
    public int getArrayLength() { return arr.length(); }

    String getJsonFromAssets() {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(FileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}

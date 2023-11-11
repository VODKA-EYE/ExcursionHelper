package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ListView;
import android.widget.TextView;
import com.example.excursionhelper.adapters.ExcursionListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
  TextView title_textview;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    title_textview = findViewById(R.id.title_textview);

    SharedPreferences prefs = getSharedPreferences("JSON", Context.MODE_PRIVATE);
    if (!prefs.getBoolean("firstTime", false))
    {
      SharedPreferences.Editor editor = prefs.edit();
      editor.putString("JSONString", loadJSONFromAsset());
      editor.putBoolean("firstTime", true);
      editor.apply();
    }
    String JSON = prefs.getString("JSONString","");

    ColorTitleText();
    SetupExcursionButtons(JSON);
  }

  private void ColorTitleText()
  {
    String title = getResources().getString(R.string.title_p1);
    SpannableString ss = new SpannableString(title);

    ForegroundColorSpan fcsYellow = new ForegroundColorSpan(getResources().getColor(R.color.yellow));
    ForegroundColorSpan fcsYellow2 = new ForegroundColorSpan(getResources().getColor(R.color.yellow));
    StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
    ss.setSpan(fcsYellow, 0,7, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    ss.setSpan(fcsYellow2, 11,13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    ss.setSpan(boldSpan, 11,13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    title_textview.setText(ss);
  }

  private void SetupExcursionButtons(String JSON)
  {
    ListView mListview = findViewById(R.id.excursions_listview);

    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<ExcursionClass>>(){}.getType();
    ArrayList<ExcursionClass> excursionArray = gson.fromJson(JSON,listType);
    ArrayList<String> titles = new ArrayList<>();
    for (ExcursionClass excursion: excursionArray)
    {
      titles.add(excursion.getTitle());
    }
    ExcursionListAdapter mAdapter = new ExcursionListAdapter(MainActivity.this, titles);
    mListview.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }
  private String loadJSONFromAsset()
  {
    String json;
    try
    {
      InputStream inputStream = getAssets().open("Excursions.json");
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      inputStream.close();
      json = new String(buffer, StandardCharsets.UTF_8);
    } catch (IOException ex)
    {
      ex.printStackTrace();
      return null;
    }
    return json;
  }
}
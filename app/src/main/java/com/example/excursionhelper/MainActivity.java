package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.excursionhelper.adapters.ExcursionListAdapter;
import com.example.excursionhelper.models.ExcursionClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
  String getDbVersionUrl = "http://vodka.chemirproject.net:5000/getdbversion";
  String getExcursionsURL = "http://vodka.chemirproject.net:5000/getexcursions";
  Integer apiDbVersion = 0;
  TextView title_textview;
  AlertDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    title_textview = findViewById(R.id.title_textview);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(false);
    builder.setView(R.layout.layout_loading_dialog);
    dialog = builder.create();
    dialog.show();
    CheckDBVersion();
    ColorTitleText();
  }

  private void CheckDBVersion()
  {
    StringRequest stringRequest = new StringRequest(Request.Method.GET, getDbVersionUrl,
      response ->
      {
        Log.e("aaaaaaaaaa","CheckDBVersion is going");
        try
        {
          JSONObject jsonObject = new JSONObject(response);
          apiDbVersion = jsonObject.getInt("version");
          SharedPreferences prefs = getSharedPreferences("JSON", Context.MODE_PRIVATE);
          if (prefs.getInt("dbVersion", 0) != apiDbVersion)
          {
            UpdateExcursionList(prefs);
          }
          else
          {
            String JSON = prefs.getString("JSONString","");
            SetupExcursionButtons(JSON);
          }
        }
        catch (JSONException e)
        {
          Log.e("aaaaaaaaaa","TryCatch in DB Check not worked! Reason:  "+e);
        }
      }, error ->
      {
        Log.e("aaaaaaaaaa","DB Check not worked! Reason:  "+error);
        SharedPreferences prefs = getSharedPreferences("JSON", Context.MODE_PRIVATE);
        String JSON = prefs.getString("JSONString","");
        if(!JSON.equals(""))
        {
          Toast.makeText(MainActivity.this, getResources().getText(R.string.content_offline), Toast.LENGTH_LONG).show();
          SetupExcursionButtons(JSON);
        }
        else
        {
          dialog.dismiss();
          Toast.makeText(MainActivity.this, getResources().getText(R.string.content_cant_load), Toast.LENGTH_LONG).show();
        }
      });
    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
    queue.add(stringRequest);
  }

  private void UpdateExcursionList(SharedPreferences prefs)
  {
    StringRequest stringRequest = new StringRequest(Request.Method.GET, getExcursionsURL,
      response ->
      {
        Log.e("aaaaaaaaaa","UpdateExcursionsList is going");
        try
        {
          SharedPreferences.Editor editor = prefs.edit();
          editor.putString("JSONString", response);
          editor.putInt("dbVersion", apiDbVersion);
          editor.apply();
          String JSON = prefs.getString("JSONString","");
          SetupExcursionButtons(JSON);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      },
      error -> Log.e("aaaaaaaaaa","UpdateExcursions not worked! Reason:  "+error));
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
  }

  private void SetupExcursionButtons(String JSON)
  {
    ListView mListview = findViewById(R.id.excursions_listview);

    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<ExcursionClass>>(){}.getType();
    ArrayList<ExcursionClass> excursionArray = gson.fromJson(JSON,listType);
    ExcursionListAdapter mAdapter = new ExcursionListAdapter(MainActivity.this, excursionArray);
    mListview.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
    dialog.dismiss();
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


  /*
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
  */
}
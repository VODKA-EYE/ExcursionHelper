package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.excursionhelper.adapters.ExcursionPageAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExcursionMainActivity extends AppCompatActivity
{
  TextView page_number, page_amount, page_title;
  Button returnToMenu, previousPage, nextPage;
  ArrayList<ExcursionPageClass> page_list;
  Integer selected_page = 0;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_excursion_main);
    page_number = findViewById(R.id.page_number_text_view);
    page_amount = findViewById(R.id.page_amount_text_view);
    page_title = findViewById(R.id.page_title_text_view);
    returnToMenu = findViewById(R.id.return_to_menu_button);
    returnToMenu.setOnClickListener(view ->
    {
      Intent intent1 = new Intent(ExcursionMainActivity.this, MainActivity.class);
      startActivity(intent1);
    });
    previousPage = findViewById(R.id.previous_page_button);
    previousPage.setOnClickListener(view ->
    {
      if(selected_page != 0)
      {
        selected_page--;
        LoadPage();
      }
    });
    nextPage = findViewById(R.id.next_page_button);
    nextPage.setOnClickListener(view ->
    {
      if(selected_page+1 != page_list.size())
      {
        selected_page++;
        LoadPage();
      }
    });

    SetupContent();
  }

  private void SetupContent()
  {
    SharedPreferences prefs = getSharedPreferences("JSON", Context.MODE_PRIVATE);
    String JSON = prefs.getString("JSONString","");
    Intent intent = getIntent();
    int excursionId = intent.getIntExtra("excursionId",0);

    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<ExcursionClass>>(){}.getType();
    ArrayList<ExcursionClass> excursionArray = gson.fromJson(JSON,listType);
    ExcursionClass excursion = excursionArray.get(excursionId);
    page_list = excursion.getExcursionStops();

    LoadPage();
  }

  private void LoadPage()
  {
    ListView page_listview = findViewById(R.id.page_items_listview);
    ExcursionPageClass page = page_list.get(selected_page);
    page_number.setText(String.valueOf(selected_page+1));
    page_amount.setText(String.valueOf(page_list.size()));
    page_title.setText(page.getTitle());
    ArrayList<String> images = page.getImageUrls();
    ArrayList<String> descriptions = page.getImageDescriptions();

    ExcursionPageAdapter mAdapter = new ExcursionPageAdapter(ExcursionMainActivity.this, images, descriptions);
    page_listview.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }
}
package com.example.excursionhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.excursionhelper.adapters.ImagesAdapter;
import com.example.excursionhelper.models.CheckpointClass;
import com.example.excursionhelper.models.ExcursionClass;
import com.example.excursionhelper.models.ImageClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ExcursionMainActivity extends AppCompatActivity
{
  TextView page_number, page_amount, page_title;
  Button previousPage, nextPage, goToMenu;
  ArrayList<CheckpointClass> page_list;
  Integer selected_page = 0, excursionId;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_excursion_main);
    if(savedInstanceState != null)
    {
      selected_page = savedInstanceState.getInt("selected_page");
    }

    page_number = findViewById(R.id.page_number_text_view);
    page_amount = findViewById(R.id.page_amount_text_view);
    page_title = findViewById(R.id.page_title_text_view);
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
    goToMenu = findViewById(R.id.goto_mainmenu_button);
    goToMenu.setOnClickListener(view -> finish());
    SetupContent();
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState)
  {
    super.onSaveInstanceState(outState);
    outState.putInt("selected_page",selected_page);
  }

  private void SetupContent()
  {
    SharedPreferences prefs = getSharedPreferences("JSON", Context.MODE_PRIVATE);
    String JSON = prefs.getString("JSONString","");
    Intent intent = getIntent();
    excursionId = intent.getIntExtra("excursionId",0);
    selected_page = intent.getIntExtra("selected_page",0);
    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<ExcursionClass>>(){}.getType();
    ArrayList<ExcursionClass> excursionArray = gson.fromJson(JSON,listType);
    ExcursionClass excursion = excursionArray.stream().filter(e -> e.getExcursionId() == excursionId).collect(Collectors.toList()).get(0);
    page_list = excursion.getCheckpoints();

    LoadPage();
  }

  private void LoadPage()
  {
    ListView page_listview = findViewById(R.id.page_items_listview);
    CheckpointClass checkpointClass = page_list.get(selected_page);
    page_number.setText(String.valueOf(selected_page+1));
    page_amount.setText(String.valueOf(page_list.size()));
    page_title.setText(checkpointClass.getTitle());
    ArrayList<ImageClass> images = checkpointClass.getImages();
    ImagesAdapter mAdapter = new ImagesAdapter(ExcursionMainActivity.this, images);
    page_listview.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }
}
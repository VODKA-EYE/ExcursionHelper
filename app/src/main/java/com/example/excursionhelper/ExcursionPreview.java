package com.example.excursionhelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.excursionhelper.adapters.CheckpointListAdapter;
import com.example.excursionhelper.models.CheckpointClass;
import com.example.excursionhelper.models.ExcursionClass;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExcursionPreview extends AppCompatActivity
{
  TextView titleTextView, mapUrlTextView, descriptionTextView, mapOpen, mapClose;
  Button goToExcursion, goToMenu, goToComments;
  PhotoView mapPhoto;
  Integer excursionId;
  AlertDialog dialog;
  ListView checkpointListView;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_excursion_preview);
    InitializeViewItems();
    LoadText();
  }

  private void InitializeViewItems()
  {
    titleTextView = findViewById(R.id.titleTextView);
    mapPhoto = findViewById(R.id.map_photo);
    goToExcursion = findViewById(R.id.begin_excursion_button);
    goToExcursion.setOnClickListener(view ->
    {
      Intent intent2 = new Intent(ExcursionPreview.this, ExcursionMainActivity.class);
      intent2.putExtra("excursionId", excursionId);
      startActivity(intent2);
    });
    goToMenu = findViewById(R.id.goto_mainmenu_button);
    goToMenu.setOnClickListener(view -> finish());
    mapUrlTextView = findViewById(R.id.map_url_open);
    descriptionTextView = findViewById(R.id.description_open);
    descriptionTextView.setOnClickListener(view -> dialog.show());
    mapOpen = findViewById(R.id.map_image_open);
    mapOpen.setOnClickListener(view ->
    {
      mapOpen.setVisibility(View.GONE);
      mapClose.setVisibility(View.VISIBLE);
      mapPhoto.setVisibility(View.VISIBLE);
    });
    mapClose = findViewById(R.id.map_image_close);
    mapClose.setOnClickListener(view ->
    {
      mapOpen.setVisibility(View.VISIBLE);
      mapClose.setVisibility(View.GONE);
      mapPhoto.setVisibility(View.GONE);
    });
    checkpointListView = findViewById(R.id.checkpoints_listview);
    goToComments = findViewById(R.id.comments_button);
    goToComments.setOnClickListener(view ->
    {
      Intent intent2 = new Intent(ExcursionPreview.this, CommentsActivity.class);
      intent2.putExtra("excursionId", excursionId);
      startActivity(intent2);
    });
  }

  private void LoadText()
  {
    Intent intent = getIntent();
    excursionId = intent.getIntExtra("excursionId",0);

    SharedPreferences prefs = getSharedPreferences("JSON",Context.MODE_PRIVATE);
    String JSON = prefs.getString("JSONString","");
    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<ExcursionClass>>(){}.getType();
    ArrayList<ExcursionClass> excursionArray = gson.fromJson(JSON,listType);
    ExcursionClass excursion = excursionArray.stream().filter(e -> e.getExcursionId() == excursionId).collect(Collectors.toList()).get(0);

    titleTextView.setText(excursion.getTitle());

    String imageUrl = excursion.getMapImageUrl();
    Ion.with(mapPhoto).load(imageUrl);

    // Set redirection to web url of map
    String mapUrl = excursion.getMapUrl();
    Pattern mapUrlPattern = Pattern.compile(getResources().getString(R.string.open_map_in_browser));
    Linkify.addLinks(mapUrlTextView, mapUrlPattern, mapUrl);

    // Set description popup
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(excursion.getDescription()).setTitle(R.string.description);
    dialog = builder.create();

    // Set checkpoint buttons
    ArrayList<CheckpointClass> checkpoints = excursion.getCheckpoints();
    CheckpointListAdapter mAdapter = new CheckpointListAdapter(this, checkpoints, excursionId);
    checkpointListView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();

    /*DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    mapPhoto.getLayoutParams().height = displayMetrics.widthPixels;
    mapPhoto.getLayoutParams().width = displayMetrics.widthPixels;
    mapPhoto.requestLayout();*/
  }


}
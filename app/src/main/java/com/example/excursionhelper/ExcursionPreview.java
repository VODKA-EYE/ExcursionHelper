package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExcursionPreview extends AppCompatActivity
{
  TextView titleTextView, descriptionTextView;
  Button goToExcursion;
  PhotoView mapPhoto;
  Integer excursionId;
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
    descriptionTextView = findViewById(R.id.descriptionTextView);
    mapPhoto = findViewById(R.id.map_photo);
    goToExcursion = findViewById(R.id.begin_excursion_button);
    goToExcursion.setOnClickListener(view ->
    {
      Intent intent2 = new Intent(ExcursionPreview.this, ExcursionMainActivity.class);
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
    ExcursionClass excursion = excursionArray.get(excursionId);

    titleTextView.setText(excursion.getTitle());
    descriptionTextView.setText(excursion.getDescription());

    String imageName = excursion.getMapImageUrl();
    int imageID = getResources().getIdentifier(imageName , "drawable", getPackageName());
    mapPhoto.setImageResource(imageID);

    /*DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    mapPhoto.getLayoutParams().height = displayMetrics.widthPixels;
    mapPhoto.getLayoutParams().width = displayMetrics.widthPixels;
    mapPhoto.requestLayout();*/
  }
}
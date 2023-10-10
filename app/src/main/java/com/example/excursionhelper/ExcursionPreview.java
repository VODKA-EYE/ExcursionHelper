package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ExcursionPreview extends AppCompatActivity
{
  TextView titleTextView, descriptionTextView;
  Button returnToMenu, goToExcursion;
  Integer excursionId;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_excursion_preview);
    titleTextView = findViewById(R.id.titleTextView);
    descriptionTextView = findViewById(R.id.descriptionTextView);
    ArrayList<String> descriptionArrays = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.excursion_descriptions)));

    Intent intent = getIntent();
    excursionId = intent.getIntExtra("excursionId",0);
    descriptionTextView.setText(descriptionArrays.get(excursionId));
    titleTextView.setText(intent.getStringExtra("excursionTitle"));

    returnToMenu = findViewById(R.id.return_to_menu_button);
    returnToMenu.setOnClickListener(view -> 
    {
      Intent intent1 = new Intent(ExcursionPreview.this, MainActivity.class);
      startActivity(intent1);
    });

    goToExcursion = findViewById(R.id.begin_excursion_button);
    goToExcursion.setOnClickListener(view -> 
    {
      Intent intent2 = new Intent(ExcursionPreview.this, ExcursionMainActivity.class);
      startActivity(intent2);
    });

  }
}
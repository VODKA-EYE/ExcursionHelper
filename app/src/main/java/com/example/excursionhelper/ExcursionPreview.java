package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ExcursionPreview extends AppCompatActivity
{
  TextView titleTextVeiw, descriptionTextView;
  private ArrayList<String> descriptionArrays;
  Integer excursionId;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_excursion_preview);
    titleTextVeiw = (TextView)findViewById(R.id.titleTextView);
    descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
    descriptionArrays = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.excursion_descriptions)));
    Intent intent = getIntent();
    excursionId = intent.getIntExtra("excursionId",0);
    descriptionTextView.setText(descriptionArrays.get(excursionId));
    titleTextVeiw.setText(intent.getStringExtra("excursionTitle"));
  }
}
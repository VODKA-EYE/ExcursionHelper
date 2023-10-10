package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ExcursionMainActivity extends AppCompatActivity
{
  Button returnToMenu;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_excursion_main);
    returnToMenu = findViewById(R.id.return_to_menu_button);
    returnToMenu.setOnClickListener(view ->
    {
      Intent intent1 = new Intent(ExcursionMainActivity.this, ExcursionPreview.class);
      startActivity(intent1);
    });
  }
}
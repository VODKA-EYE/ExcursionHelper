package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.excursionhelper.adapters.ExcursionListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ListView mListview = findViewById(R.id.excursions_listview);
    ArrayList<String> mArrData = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.excursion_names)));
    ExcursionListAdapter mAdapter = new ExcursionListAdapter(MainActivity.this, mArrData);
    mListview.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }
}
package com.example.excursionhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.excursionhelper.adapters.ExcursionListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
  private ListView mListview;
  private ArrayList<String> mArrData;
  private ExcursionListAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mListview = (ListView) findViewById(R.id.excursions_listview);
    mArrData = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.excursion_names)));
    mAdapter = new ExcursionListAdapter(MainActivity.this, mArrData);
    mListview.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }
}
package com.example.excursionhelper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.excursionhelper.R;

import java.util.ArrayList;

public class ExcursionListAdapter extends BaseAdapter
{
  private Context mContext;
  private ArrayList<String> mArrData;

  public ExcursionListAdapter(Context context, ArrayList arrData)
  {
    super();
    mContext = context;
    mArrData = arrData;
  }

  public int getCount()
  {
    return mArrData.size();
  }

  public View getView(int position, View view, ViewGroup parent)
  {
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.excursion_button, parent, false);
    Button excursion_button = (Button) view.findViewById(R.id.excursion_button);
    excursion_button.setText(mArrData.get(position));
    excursion_button.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        // Logic goes here
      }
    });

    return view;
  }

  public Object getItem(int position)
  {
    // TODO Auto-generated method stub
    return position;
  }

  public long getItemId(int position) {
    // TODO Auto-generated method stub
    return position;
  }
}
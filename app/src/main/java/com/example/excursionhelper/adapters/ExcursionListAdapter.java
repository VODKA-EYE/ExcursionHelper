package com.example.excursionhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import com.example.excursionhelper.ExcursionPreview;
import com.example.excursionhelper.R;

import java.util.ArrayList;

public class ExcursionListAdapter extends BaseAdapter
{
  private Context mContext;
  private ArrayList<String> mArrData;

  public ExcursionListAdapter(Context context, ArrayList arrayList)
  {
    super();
    mContext = context;
    mArrData = arrayList;
  }

  public int getCount()
  {
    return mArrData.size();
  }

  public View getView(int position, View view, ViewGroup parent)
  {
    //if(view == null)
    //{
      LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.excursion_button, parent, false);
      Button excursion_button = view.findViewById(R.id.excursion_button);
      excursion_button.setText(mArrData.get(position));
      excursion_button.setOnClickListener(view1 ->
      {
        Intent intent = new Intent(mContext, ExcursionPreview.class);
        intent.putExtra("excursionId", position);
        view1.getContext().startActivity(intent);
      });
    //}
    return view;
  }

  public Object getItem(int position)
  {
    return position;
  }

  public long getItemId(int position)
  {
    return position;
  }
}

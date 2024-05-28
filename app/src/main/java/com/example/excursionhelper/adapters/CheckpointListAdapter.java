package com.example.excursionhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.excursionhelper.ExcursionMainActivity;
import com.example.excursionhelper.ExcursionPreview;
import com.example.excursionhelper.R;
import com.example.excursionhelper.models.CheckpointClass;

import java.util.ArrayList;

public class CheckpointListAdapter extends BaseAdapter
{
  private Context mContext;
  private ArrayList<CheckpointClass> mArrData;
  private Integer mExcursionId;

  public CheckpointListAdapter(Context context, ArrayList<CheckpointClass> arrayList, Integer excursionId)
  {
    super();
    mContext = context;
    mArrData = arrayList;
    mExcursionId = excursionId;
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
    view = inflater.inflate(R.layout.checkpoint_list_item, parent, false);

    TextView textView = view.findViewById(R.id.text);
    String text = mArrData.get(position).getOrderNumber() + ". " + mArrData.get(position).getTitle();
    textView.setText(text);
    textView.setOnClickListener(view1 ->
    {
      Intent intent = new Intent(mContext, ExcursionMainActivity.class);
      intent.putExtra("excursionId", mExcursionId);
      intent.putExtra("selected_page", position);
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

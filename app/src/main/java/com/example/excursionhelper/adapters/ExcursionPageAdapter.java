package com.example.excursionhelper.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.excursionhelper.R;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.ArrayList;

public class ExcursionPageAdapter extends BaseAdapter
{
  private final Context mContext;
  private final ArrayList<String> images;
  private final ArrayList<String> descriptions;

  public ExcursionPageAdapter(Context context, ArrayList images, ArrayList descriptions)
  {
    super();
    mContext = context;
    this.images = images;
    this.descriptions = descriptions;
  }

  public int getCount()
  {
    return images.size();
  }

  public View getView(int position, View view, ViewGroup parent)
  {
    //if (view == null)
    //{
      LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.excursion_page_item, parent, false);
      PhotoView photoView = view.findViewById(R.id.photo);

      String imageName = images.get(position);
      int imageID = mContext.getResources().getIdentifier(imageName , "drawable", mContext.getPackageName());
      photoView.setImageResource(imageID);
      DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
      if(displayMetrics.widthPixels > displayMetrics.heightPixels)
      {
        //photoView.getLayoutParams().width
      }

      TextView descriptionView = view.findViewById(R.id.image_description);
      descriptionView.setText(descriptions.get(position));
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

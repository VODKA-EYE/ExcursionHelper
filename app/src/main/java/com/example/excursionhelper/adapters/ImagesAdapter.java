package com.example.excursionhelper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.excursionhelper.models.ImageClass;
import com.example.excursionhelper.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class ImagesAdapter extends BaseAdapter
{
  private final Context mContext;
  private final ArrayList<ImageClass> images;

  public ImagesAdapter(Context context, ArrayList<ImageClass> images)
  {
    super();
    mContext = context;
    this.images = images;
  }

  public int getCount()
  {
    return images.size();
  }

  public View getView(int position, View view, ViewGroup parent)
  {

      LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.excursion_page_item, parent, false);

      PhotoView photoView = view.findViewById(R.id.photo);
      Ion.with(photoView).load(images.get(position).getImageUrl());

      TextView descriptionView = view.findViewById(R.id.image_description);
      descriptionView.setText(images.get(position).getImageDescription());

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

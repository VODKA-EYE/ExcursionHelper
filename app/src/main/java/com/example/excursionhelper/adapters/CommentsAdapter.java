package com.example.excursionhelper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.excursionhelper.R;
import com.example.excursionhelper.models.CommentClass;

import java.util.ArrayList;

public class CommentsAdapter extends BaseAdapter
{
  private final Context mContext;
  private final ArrayList<CommentClass> comments;

  public CommentsAdapter(Context context, ArrayList<CommentClass> comments)
  {
    super();
    mContext = context;
    this.comments = comments;
  }

  public int getCount()
  {
    return comments.size();
  }

  public View getView(int position, View view, ViewGroup parent)
  {
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.comment_list_item, parent, false);

    TextView commentatorName = view.findViewById(R.id.commentatorName);
    TextView commentDate = view.findViewById(R.id.commentDate);
    TextView commentContent = view.findViewById(R.id.commentContent);

    commentatorName.setText(comments.get(position).getCommentator());
    String originalDate = comments.get(position).getCommentDate();
    String date = originalDate.substring(0, originalDate.indexOf('T'));
    String time = originalDate.substring(originalDate.indexOf('T')+1, originalDate.indexOf('.'));
    commentDate.setText(time + " " + date);
    commentContent.setText(comments.get(position).getComment());

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

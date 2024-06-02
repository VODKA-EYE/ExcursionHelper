package com.example.excursionhelper;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.excursionhelper.adapters.CommentsAdapter;
import com.example.excursionhelper.adapters.ExcursionListAdapter;
import com.example.excursionhelper.models.CommentClass;
import com.example.excursionhelper.models.ExcursionClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

  Button returnButton, leaveCommentButton;
  Integer excursionId;
  String commentsUrl = "http://vodka.chemirproject.net:5000/getcomments/";
  AlertDialog dialog;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comments);
    InitializeViewItems();
    LoadComments();
  }

  @Override
  protected void onRestart()
  {
    super.onRestart();
    LoadComments();
  }
  private void InitializeViewItems()
  {
    returnButton = findViewById(R.id.returnButton);
    returnButton.setOnClickListener(view -> finish());

    leaveCommentButton = findViewById(R.id.buttonLeaveComment);
    leaveCommentButton.setOnClickListener(view ->
    {
      Intent intent2 = new Intent(CommentsActivity.this, CommentActivity.class);
      intent2.putExtra("excursionId", excursionId);
      startActivity(intent2);

    });

    Intent intent = getIntent();
    excursionId = intent.getIntExtra("excursionId",0);
    commentsUrl = commentsUrl + excursionId;

    android.app.AlertDialog.Builder ADbuilder = new android.app.AlertDialog.Builder(this);
    ADbuilder.setCancelable(false);
    ADbuilder.setView(R.layout.layout_loading_dialog);
    dialog = ADbuilder.create();
  }

  private void LoadComments()
  {
    dialog.show();
    StringRequest stringRequest = new StringRequest(Request.Method.GET, commentsUrl,
      response -> SetupComments(response),
      error ->
      {
        dialog.dismiss();
        Toast.makeText(CommentsActivity.this, getResources().getText(R.string.comments_cant_load), Toast.LENGTH_LONG).show();
      });
    RequestQueue queue = Volley.newRequestQueue(CommentsActivity.this);
    queue.add(stringRequest);
  }

  private void SetupComments(String JSON)
  {
    ListView commentsListView = findViewById(R.id.comments_listview);
    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<CommentClass>>(){}.getType();
    ArrayList<CommentClass> commentArray = gson.fromJson(JSON,listType);
    CommentsAdapter mAdapter = new CommentsAdapter(CommentsActivity.this, commentArray);
    commentsListView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
    dialog.dismiss();
  }



}
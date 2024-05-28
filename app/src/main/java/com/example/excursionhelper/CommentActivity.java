package com.example.excursionhelper;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.excursionhelper.models.CommentClass;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;


public class CommentActivity extends AppCompatActivity {
  String leaveCommentUrl = "http://vodka.chemirproject.net:5000/leavecomment";
  EditText etName, etComment;
  Button buttonBack, buttonLeaveComment;
  AlertDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comment);
    etName = findViewById(R.id.editTextUserName);
    etComment = findViewById(R.id.editTextComment);
    buttonBack = findViewById(R.id.returnButton);
    buttonBack.setOnClickListener(view -> finish());
    buttonLeaveComment = findViewById(R.id.buttonLeaveComment);
    buttonLeaveComment.setOnClickListener(view ->
    {
      try {
        leaveComment();
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private void leaveComment() throws JSONException
  {
    if (!etName.getText().toString().equals("") || !etComment.getText().toString().equals(""))
    {
      // Loading on comment
      AlertDialog.Builder ADbuilder = new AlertDialog.Builder(this);
      ADbuilder.setCancelable(false);
      ADbuilder.setView(R.layout.layout_loading_dialog);
      dialog = ADbuilder.create();
      dialog.show();
      // Fetching data
      Intent intent = getIntent();
      Integer excursionID = intent.getIntExtra("excursionId", 0);
      CommentClass comment = new CommentClass();
      comment.setCommentator(etName.getText().toString());
      comment.setComment(etComment.getText().toString());
      comment.setExcursionID(excursionID);
      JSONObject jsonBody = new JSONObject(new Gson().toJson(comment));

      JsonObjectRequest getrequest = new JsonObjectRequest(Request.Method.POST,
        leaveCommentUrl, jsonBody, response ->
        {
          NotificationCompat.Builder builder = new NotificationCompat
          .Builder(CommentActivity.this, "commentPublishedChannel")
          .setSmallIcon(R.drawable.logo)
          .setContentTitle(getResources().getString(R.string.comment_published));
          if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
          {
            NotificationManagerCompat notificationManagerCompat = getSystemService(NotificationManagerCompat.class);
            notificationManagerCompat.notify(111, builder.build());
          }
          dialog.dismiss();
          finish();
        }, error ->
      {
        dialog.dismiss();
        Toast.makeText(CommentActivity.this, getResources().getText(R.string.comment_not_published), Toast.LENGTH_LONG).show();
      });
      RequestQueue requestQueue = Volley.newRequestQueue(this);
      requestQueue.add(getrequest);
    }
  }
}
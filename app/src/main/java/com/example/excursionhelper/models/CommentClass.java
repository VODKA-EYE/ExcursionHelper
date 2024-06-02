package com.example.excursionhelper.models;

import java.time.LocalDateTime;

public class CommentClass
{

  String commentator, commentary, commentDate;
  Integer excursionID;

  public Integer getExcursionID()
  {
    return excursionID;
  }

  public void setExcursionID(Integer excursionID)
  {
    this.excursionID = excursionID;
  }

  public String getCommentator(){return commentator;}

  public void setCommentator(String commentator){this.commentator = commentator;}

  public String getComment(){return commentary;}

  public void setComment(String commentary){this.commentary = commentary;}
  public String getCommentDate(){return commentDate;}
  public void setCommentDate(String commentDate) {this.commentDate = commentDate;  }
}

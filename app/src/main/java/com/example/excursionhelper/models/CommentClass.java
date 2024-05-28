package com.example.excursionhelper.models;

public class CommentClass
{

  String commentator, commentary;

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
}

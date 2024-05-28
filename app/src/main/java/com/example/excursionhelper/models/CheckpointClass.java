package com.example.excursionhelper.models;

import java.util.ArrayList;

public class CheckpointClass
{
  private String title;

  private short orderNumber;

  private ArrayList<ImageClass> images;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public short getOrderNumber() {return orderNumber;}

  public void setOrderNumber(short orderNumber) {this.orderNumber = orderNumber;}

  public ArrayList<ImageClass> getImages() {return images;}

  public void setImages(ArrayList<ImageClass> images) {this.images = images;}

}

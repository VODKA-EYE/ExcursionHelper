package com.example.excursionhelper;

import java.util.ArrayList;

public class ExcursionPageClass
{
  private String title;
  private ArrayList<String> imageUrls, imageDescriptions;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public ArrayList<String> getImageUrls()
  {
    return imageUrls;
  }

  public void setImageUrls(ArrayList<String> imageUrls)
  {
    this.imageUrls = imageUrls;
  }

  public ArrayList<String> getImageDescriptions()
  {
    return imageDescriptions;
  }

  public void setImageDescriptions(ArrayList<String> imageDescriptions)
  {
    this.imageDescriptions = imageDescriptions;
  }
}

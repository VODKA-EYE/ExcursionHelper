package com.example.excursionhelper;

import java.util.ArrayList;

public class ExcursionClass
{
  private String title, description, mapImageUrl;
  private ArrayList<ExcursionPageClass> excursionStops;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }
  public String getMapImageUrl()
  {
    return mapImageUrl;
  }

  public void setMapImageUrl(String mapImageUrl)
  {
    this.mapImageUrl = mapImageUrl;
  }

  public ArrayList<ExcursionPageClass> getExcursionStops()
  {
    return excursionStops;
  }

}

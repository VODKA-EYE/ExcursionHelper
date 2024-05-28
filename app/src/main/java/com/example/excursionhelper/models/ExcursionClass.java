package com.example.excursionhelper.models;

import java.util.ArrayList;

public class ExcursionClass
{
  private int excursionId;
  private String title, description, mapImageUrl, mapUrl;
  private ArrayList<CheckpointClass> checkpoints;

  public Integer getExcursionId()
  {
    return excursionId;
  }

  public void setExcursionId(Integer excursionId)
  {
    this.excursionId = excursionId;
  }

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

  public String getMapUrl()
  {
    return mapUrl;
  }

  public void setMapUrl(String mapUrl)
  {
    this.mapUrl = mapUrl;
  }

  public ArrayList<CheckpointClass> getCheckpoints()
  {
    return checkpoints;
  }

  public void setCheckpoints(ArrayList<CheckpointClass> checkpoints){this.checkpoints = checkpoints;}

}

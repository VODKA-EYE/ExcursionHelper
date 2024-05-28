package com.example.excursionhelper.models;

import java.sql.Date;
import java.time.LocalDateTime;

public class DbVersionClass
{
  Integer version;

  LocalDateTime timestamp;

  public Integer getDbVersion()
  {
    return version;
  }

  public void setDbVersion(Integer version)
  {
    this.version = version;
  }

  public 	LocalDateTime getTimestamp() {return timestamp;}

  public void setTimestamp(Date version)
  {
    this.timestamp = timestamp;
  }

}

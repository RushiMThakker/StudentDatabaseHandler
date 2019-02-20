package com.example.rushi.studentdatabasehandler.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.io.Serializable;

@Entity(tableName = "student")
public class Student implements Serializable
{
  @PrimaryKey(autoGenerate = true)
  private Integer id;
  private String firstName, lastName, color;

  public Student(String firstName, String lastName, String color)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.color = color;
  }

  @NonNull public Integer getId()
  {
    return id;
  }

  public void setId(@NonNull Integer id)
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getColor()
  {
    return color;
  }

  public void setColor(String color)
  {
    this.color = color;
  }
}

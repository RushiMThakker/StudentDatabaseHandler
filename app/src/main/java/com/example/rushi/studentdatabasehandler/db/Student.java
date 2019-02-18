package com.example.rushi.studentdatabasehandler.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "student")
public class Student
{
  @PrimaryKey(autoGenerate = true)
  @NonNull
  private Integer id;
  private String firstName, lastName, color;

  public Student(@NonNull Integer id, String firstName, String lastName, String color)
  {
    this.id = id;
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

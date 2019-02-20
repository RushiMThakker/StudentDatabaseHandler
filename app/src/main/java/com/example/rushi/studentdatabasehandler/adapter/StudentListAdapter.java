package com.example.rushi.studentdatabasehandler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rushi.studentdatabasehandler.R;
import com.example.rushi.studentdatabasehandler.db.Student;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>
{
  class StudentViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView studentItemView;

    private StudentViewHolder(View itemView)
    {
      super(itemView);
      studentItemView = itemView.findViewById(R.id.textView);
    }
  }

  private final LayoutInflater mInflater;
  private List<Student> mStudents; // Cached copy of words

  public StudentListAdapter(Context context)
  {
    mInflater = LayoutInflater.from(context);
  }

  @NonNull @Override
  public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
    View itemView = mInflater.inflate(R.layout.custom_row_student, parent, false);
    return new StudentViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull StudentViewHolder holder, int position)
  {
    if (mStudents != null)
    {
      Student current = mStudents.get(position);
      holder.studentItemView.setText(current.getFirstName());
    } else
    {
      // Covers the case of data not being ready yet.
      holder.studentItemView.setText("No Student");
    }
  }

  public void setStudents(List<Student> words)
  {
    mStudents = words;
    notifyDataSetChanged();
  }

  // getItemCount() is called many times, and when it is first called,
  // mStudents has not been updated (means initially, it's null, and we can't return null).
  @Override
  public int getItemCount()
  {
    if (mStudents != null)
    {
      return mStudents.size();
    } else
    {
      return 0;
    }
  }
}
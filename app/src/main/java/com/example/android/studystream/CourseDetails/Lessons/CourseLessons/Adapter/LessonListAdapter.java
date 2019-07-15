package com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Data.Models.Lesson;
import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.Models.Material;
import com.example.android.studystream.R;

import java.util.List;

public class LessonListAdapter extends ArrayAdapter<Lesson> {
    private boolean mUserType;
    private String  mUserEmail;
    private Context mContext;
    public LessonListAdapter(@NonNull Context context, List<Lesson> lessons , String email , boolean userType , int CourseCode) {
        super(context, 0,lessons);
        mUserType = userType;
        mUserEmail = email;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Lesson Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.lesson_list_item,parent,false);
        }

        TextView lessonTitle = (TextView) ListItemView.findViewById(R.id.LessonListItem_Title_TextView);

        lessonTitle.setText(Item.GetTitle().toString());

        //setting click listener
        LinearLayout lessonParent = (LinearLayout)ListItemView.findViewById(R.id.LessonListItem_Parent_Layout);
        lessonParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
            }
        });


        return ListItemView;
    }
}

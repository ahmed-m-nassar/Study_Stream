package com.example.android.studystream.CoursesHomePage.Adapter;

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

import com.example.android.studystream.CourseDetails.CourseDetailsActivity;
import com.example.android.studystream.CoursesHomePage.Data.Models.Course;
import com.example.android.studystream.R;

import java.util.List;

public class CourseListAdapter extends ArrayAdapter<Course> {
    private boolean mUserType;
    private String  mUserEmail;
    private Context mContext;
    public CourseListAdapter(@NonNull Context context, List<Course> courses ,String email ,  boolean userType) {
        super(context, 0,courses);
        mUserType = userType;
        mUserEmail = email;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Course Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.course_list_item,parent,false);
        }

        //setting essential Course properties
        ////////////////////////////////////////////////////////////////
        TextView courseTitle = (TextView)ListItemView.findViewById(R.id.CourseListItem_CourseTitle_TextView);
        TextView courseDescription = (TextView)ListItemView.findViewById(R.id.CourseListItem_CourseDescription_TextView);

        courseTitle.setText(Item.getmCourseTitle());
        courseDescription.setText(Item.getmCourseDescription());
        /////////////////////////////////////////////////////////////////

        //setting the rest of course properties according to user type
        ///////////////////////////////////////////////////////////////////////////
        if(mUserType == true) { //if the user was a doctor we should add Course code and password properties
            TextView courseCode = (TextView)ListItemView.findViewById(R.id.CourseListItem_CourseCode_TextView);
            TextView coursePassword = (TextView)ListItemView.findViewById(R.id.CourseListItem_CoursePassword_TextView);

            courseCode.setText(String.valueOf(Item.getmCourseCode()));
            coursePassword.setText(Item.getmCoursePassword());

            //removing the rest of the course properties
            (ListItemView.findViewById(R.id.CourseListItem_CourseProfessor_TextView)).setVisibility(View.GONE);

        } else { //if the user was a student we should add Course professor property
            TextView courseProfessor = (TextView)ListItemView.findViewById(R.id.CourseListItem_CourseProfessor_TextView);

            courseProfessor.setText(Item.getmCourseProfessor());

            //removing the rest of course properties
            (ListItemView.findViewById(R.id.CourseListItem_CourseCode_TextView)).setVisibility(View.GONE);
            (ListItemView.findViewById(R.id.CourseListItem_CoursePassword_TextView)).setVisibility(View.GONE);
        }
        ///////////////////////////////////////////////////////////////////////////

        //Setting click listener
        LinearLayout parentLayout = (LinearLayout)ListItemView.findViewById(R.id.CourseListItem_CourseParentView_Layout);
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , CourseDetailsActivity.class);
                //extras
                intent.putExtra("Email" , mUserEmail);
                intent.putExtra("UserType" , mUserType);
                intent.putExtra("CourseCode" , Item.getmCourseCode());

                //starting activity
                mContext.startActivity(intent);
            }
        });
        return ListItemView;
    }
}

package com.example.android.studystream.CoursesStatistics.Adapter;

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
import com.example.android.studystream.CoursesStatistics.Model.Statistics;
import com.example.android.studystream.R;

import java.util.List;

public class CourseStatisticsAdapter extends ArrayAdapter<Statistics> {

    private String  mUserEmail;
    private Context mContext;
    public CourseStatisticsAdapter(@NonNull Context context, List<Statistics> statistics  ) {
        super(context, 0,statistics);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Statistics Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.statistics_list_item,parent,false);
        }

        //getting views
        /////////////////////////////////////////////////////////////////////////////////////////////////
        TextView courseTitle = (TextView)ListItemView.findViewById(R.id.StatisticsListItem_CourseTitle_TextView);
        TextView lessonsNumber = (TextView)ListItemView.findViewById(R.id.StatisticsListItem_LessonNumber_TextView);
        TextView materialsNumber = (TextView)ListItemView.findViewById(R.id.StatisticsListItem_MaterialNumber_TextView);
        TextView announcementsNumber = (TextView)ListItemView.findViewById(R.id.StatisticsListItem_AnnouncementsNumber_TextView);
        TextView questionsNumber = (TextView)ListItemView.findViewById(R.id.StatisticsListItem_QuestionsNumber_TextView);
        TextView answersNumber = (TextView)ListItemView.findViewById(R.id.StatisticsListItem_AnswersNumber_TextView);
        ///////////////////////////////////////////////////////////////////////////////////////////////

        //setting data
        courseTitle.setText(String.valueOf(Item.getmCourseTitle()));
        lessonsNumber.setText(String.valueOf(Item.getmLessonsNum()));
        materialsNumber.setText(String.valueOf(Item.getmMaterialsNum()));
        announcementsNumber.setText(String.valueOf(Item.getmAnnouncementsNum()));
        questionsNumber.setText(String.valueOf(Item.getmQuestionsNum()));
        answersNumber.setText(String.valueOf(Item.getmAnswersNum()));
        /////////////////////////////////////////////////////////////////

        return ListItemView;
    }

}

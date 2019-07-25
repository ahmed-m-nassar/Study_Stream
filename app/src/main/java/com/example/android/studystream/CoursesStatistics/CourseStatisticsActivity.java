package com.example.android.studystream.CoursesStatistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.studystream.CoursesStatistics.Adapter.CourseStatisticsAdapter;
import com.example.android.studystream.CoursesStatistics.Model.Statistics;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CourseStatisticsActivity extends AppCompatActivity implements CourseStatisticsContract.View {

    private CourseStatisticsPresenter  mPresenter;
    private ListView                   mList;
    private String                     mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_statistics);

        //setting private variables
        /////////////////////////////////////////////////////////

        //presenter
        mPresenter = new CourseStatisticsPresenter(this);

        //views
        mList = (ListView)findViewById(R.id.CourseStatistics_CoursesStatistics_ListView);

        //extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");

        //////////////////////////////////////////////////////////


        mPresenter.getCoursesStatistics(mEmail);
    }

    @Override
    public void fillCoursesStatistics(ArrayList<Statistics> statistics) {
        CourseStatisticsAdapter adapter = new CourseStatisticsAdapter(this , statistics );
        mList.setAdapter(adapter);
    }
}

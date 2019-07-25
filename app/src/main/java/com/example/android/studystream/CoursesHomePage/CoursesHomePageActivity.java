package com.example.android.studystream.CoursesHomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.studystream.CourseDetails.CourseDetailsActivity;
import com.example.android.studystream.CoursesHomePage.Adapter.CourseListAdapter;
import com.example.android.studystream.CoursesHomePage.Data.Models.Course;
import com.example.android.studystream.JoinCourse.JoinCourseActivity;
import com.example.android.studystream.NewCourse.NewCourseActivity;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CoursesHomePageActivity extends AppCompatActivity implements CoursesHomePageContract.View {

    private CoursesHomePagePresenter mPresenter;
    private String           mUserEmail;
    private boolean          mUserType;
    private ListView         mCoursesList;
    private TextView         mBioText;
    private LinearLayout     mDoctorBioParent;
    private Button           mJoinCourse;
    private Button           mAddCourse;
    private Button           mEditBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        //initializing variables
        /////////////////////////////////////////////////////////////////////////////////

        //getting extras
        Intent intent = getIntent();
        mUserEmail = intent.getStringExtra("Email");
        mUserType =  intent.getBooleanExtra("UserType" , true);

        //getting views
        mCoursesList = (ListView)findViewById(R.id.Courses_Courses_List);
        mDoctorBioParent = (LinearLayout)findViewById(R.id.Courses_Bio_Parent);
        mJoinCourse = (Button)findViewById(R.id.Courses_JoinCourse_Button);
        mAddCourse = (Button)findViewById(R.id.Courses_AddNewCourse_Button);
        mEditBio = (Button)findViewById(R.id.Courses_BioEdit_Button);
        mBioText = (TextView)findViewById(R.id.Courses_BioText_TextView);

        //getting presenter
        mPresenter = new CoursesHomePagePresenter(this);

        //////////////////////////////////////////////////////////////////////////////////

        //setting click listeners
        ////////////////////////////////////////////////////
        //add course button click listener
        mAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNewCourseScreen();
            }
        });

        //Join course button click listener
        mJoinCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToJoinCourseScreen();
            }
        });


        //////////////////////////////////////////////////

        //getting data according to user type
        getUserData();
    }



    @Override
    protected void onResume() {
        super.onResume();

        //getting data according to user type
        getUserData();
    }

    @Override
    public void fillCoursesList(ArrayList<Course> courses) {
        CourseListAdapter adapter = new CourseListAdapter(this,courses,mUserEmail,mUserType);
        mCoursesList.setAdapter(adapter);

    }

    @Override
    public void fillDoctorBio(String bio) {
        mBioText.setText(bio);
    }

    @Override
    public void navigateToCourseDetailsScreen() {
        Intent intent = new Intent(this, CourseDetailsActivity.class);
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }

    @Override
    public void navigateToChangePasswordScreen() {
        Intent intent = new Intent();
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }

    @Override
    public void navigateToCoursesStatisticsScreen() {
        Intent intent = new Intent();
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }

    @Override
    public void navigateToNewCourseScreen() {
        Intent intent = new Intent(this, NewCourseActivity.class);
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }

    @Override
    public void navigateToJoinCourseScreen() {
        Intent intent = new Intent(this, JoinCourseActivity.class);
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }

    @Override
    public void navigateToChangeBioScreen() {
        Intent intent = new Intent();
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }


    //region Utility functions

    private void adjustLayoutToDoctor() {
        mJoinCourse.setVisibility(View.GONE);
    }

    private void adjustLayoutToStudent() {
        mDoctorBioParent.setVisibility(View.GONE);
        mAddCourse.setVisibility(View.GONE);
    }

    private void getUserData () {
        if(mUserType == true) { //if the user is a doctor we should romove some items from the layout
            adjustLayoutToDoctor();
            mPresenter.getDoctorData(mUserEmail);
        } else { //if the user is a student we should remove some items from the layout
            adjustLayoutToStudent();
            mPresenter.getStudentData(mUserEmail);
        }
    }

    //endregion
}

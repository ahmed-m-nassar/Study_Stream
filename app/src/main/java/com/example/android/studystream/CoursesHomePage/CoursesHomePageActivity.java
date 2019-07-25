package com.example.android.studystream.CoursesHomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.studystream.CourseDetails.CourseDetailsActivity;
import com.example.android.studystream.CoursesHomePage.Adapter.CourseListAdapter;
import com.example.android.studystream.CoursesHomePage.Data.Models.Course;
import com.example.android.studystream.CoursesStatistics.CourseStatisticsActivity;
import com.example.android.studystream.EditBio.EditBioActivity;
import com.example.android.studystream.JoinCourse.JoinCourseActivity;
import com.example.android.studystream.Main.MainActivity;
import com.example.android.studystream.NewCourse.NewCourseActivity;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CoursesHomePageActivity extends AppCompatActivity implements CoursesHomePageContract.View {

    private CoursesHomePagePresenter mPresenter;
    private String                   mUserEmail;
    private boolean                  mUserType;
    private ListView                 mCoursesList;
    private TextView                 mBioText;
    private LinearLayout             mDoctorBioParent;
    private Button                   mJoinCourse;
    private Button                   mAddCourse;
    private Button                   mEditBio;
    private Toolbar                  mToolBar;
    private long                     mLastBackPressingTime;

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
        mToolBar = findViewById(R.id.Courses_ToolBar);

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
        //toolbar settings
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Welcome " + mUserEmail);

        //getting data according to user type
        getUserData();
    }

    //region activity overriden methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_page_menu , menu);
        return  true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //getting data according to user type
        getUserData();
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem courseStatistics = menu.findItem(R.id.HomePage_CoursesStatistics_MenuItem);
        if(mUserType)
        {
            courseStatistics.setVisible(true);
        }
        else
        {
            courseStatistics.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.HomePage_CoursesStatistics_MenuItem:
                mPresenter.courseStatisticsItemClicked();
                break;
            case R.id.HomePage_ChangePassword_MenuItem:
                mPresenter.changePasswordItemClicked();
                break;
            case R.id.HomePage_LogOut_MenuItem:
                mPresenter.logoutItemClicked(mUserEmail);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        mPresenter.backButtonPressed(mLastBackPressingTime);
        mLastBackPressingTime = System.currentTimeMillis();
    }

    //endregion

    //region Course home page contract interface
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
        Intent intent = new Intent(this , CourseStatisticsActivity.class);
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
        Intent intent = new Intent(this , EditBioActivity.class);
        intent.putExtra("Email" , mUserEmail);
        startActivity(intent);
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void finishScreen() {
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void exitApplication() {
        System.exit(0);
    }
    //endregion

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

package com.example.android.studystream.CourseDetails;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.CourseAnnouncementsFragment;
import com.example.android.studystream.CourseDetails.Lessons.CourseLessons.CourseLessonsFragment;
import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.CourseMaterialsFragment;
import com.example.android.studystream.R;

public class CourseDetailsActivity extends AppCompatActivity {
    private String               mEmail;
    private boolean              mUserType;
    private int                  mCourseCode;
    private FragmentManager      mFragmentManager;
    private BottomNavigationView mBottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        //getting extras
        Intent intent = getIntent();
        mEmail = intent.getStringExtra("Email");
        mUserType = intent.getBooleanExtra("UserType" , false);
        mCourseCode = intent.getIntExtra("CourseCode" , -1);

        //fragment manager
        mFragmentManager = getSupportFragmentManager();

        //setting buttom navigation
        mBottomNav = findViewById(R.id.CourseDetails_Navigation_View);
        mBottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            Fragment fragment = new CourseLessonsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Email", mEmail);
            bundle.putInt("CourseCode" , mCourseCode);
            bundle.putBoolean("UserType" , mUserType);
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.CourseDetails_Container_FragmentLayout,
                    fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       /*mFragmentManager.popBackStack();
       mFragmentManager.executePendingTransactions();
       String selectedFragment = mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getName();*/
       /*if(Integer.valueOf( selectedFragment) == R.id.CourseDetailsMenu_Lessons_MenuItem ||
          Integer.valueOf( selectedFragment) == R.id.CourseDetailsMenu_Announcements_MenuItem ||
          Integer.valueOf( selectedFragment) == R.id.CourseDetailsMenu_Materials_MenuItem) {

           for (int i = 0; i < mBottomNav.getMenu().size(); i++) {
               MenuItem menuItem = mBottomNav.getMenu().getItem(i);
               boolean isChecked = menuItem.getItemId() == Integer.valueOf( selectedFragment);
               menuItem.setChecked(isChecked);
           }
       }*/

        //if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            if (getSupportFragmentManager().findFragmentById(R.id.CourseDetails_Container_FragmentLayout) instanceof CourseMaterialsFragment) {
                CourseDetailsActivity.this.finish();
            } else if (getSupportFragmentManager().findFragmentById(R.id.CourseDetails_Container_FragmentLayout) instanceof CourseLessonsFragment) {
                CourseDetailsActivity.this.finish();
            } else if (getSupportFragmentManager().findFragmentById(R.id.CourseDetails_Container_FragmentLayout) instanceof CourseAnnouncementsFragment) {
                CourseDetailsActivity.this.finish();
            }else {
                super.onBackPressed();
            }
       /* }else {
            finish();
        }*/



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.CourseDetailsMenu_Lessons_MenuItem:
                            selectedFragment = new CourseLessonsFragment();
                            break;
                        case R.id.CourseDetailsMenu_Announcements_MenuItem:
                            selectedFragment = new CourseAnnouncementsFragment();
                            break;
                        case R.id.CourseDetailsMenu_Materials_MenuItem:
                            selectedFragment = new CourseMaterialsFragment();
                            break;
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("Email", mEmail);
                    bundle.putInt("CourseCode" , mCourseCode);
                    bundle.putBoolean("UserType" , mUserType);
                    selectedFragment.setArguments(bundle);



                    getSupportFragmentManager().beginTransaction().replace(R.id.CourseDetails_Container_FragmentLayout,
                            selectedFragment).addToBackStack(null).commit();

                    return true;
                }
            };


}

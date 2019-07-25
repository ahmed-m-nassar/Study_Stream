package com.example.android.studystream.CourseDetails.Lessons.CourseLessons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.CourseDetails.Lessons.CourseLessons.Adapter.LessonListAdapter;
import com.example.android.studystream.CourseDetails.Lessons.Models.Lesson;
import com.example.android.studystream.CourseDetails.Lessons.NewLesson.NewLessonActivity;
import com.example.android.studystream.Questions.LessonQuestions.LessonQuestionsActivity;
import com.example.android.studystream.R;

import java.util.ArrayList;

public class CourseLessonsFragment extends Fragment implements CourseLessonsContract.View {
    private CourseLessonsPresenter mPresenter;
    private ListView               mLessonsList;
    private Button                 mAddLesson;
    private String                 mEmail;
    private boolean                mUserType;
    private int                    mCourseCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_lessons, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        //////////////////////////////////////////////////////

        //presenter
        mPresenter = new CourseLessonsPresenter(this);

        //views
        mLessonsList = (ListView) view.findViewById(R.id.CourseLessons_LessonsList_ListView);
        mAddLesson   = (Button)view.findViewById(R.id.CourseLessons_AddLesson_Button);

        //bundle
        Bundle bundle = getArguments();
        mEmail = bundle.getString("Email");
        mUserType = bundle.getBoolean("UserType" , false);
        mCourseCode = bundle.getInt("CourseCode" , -1);

        ////////////////////////////////////////////////////

        //setting click listeners
        mAddLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addLessonButtonClicked();
            }
        });

        adjustLayout();

        //getting lessons list
        mPresenter.getLessonList(mCourseCode);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getLessonList(mCourseCode);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void fillLessonsList(ArrayList<Lesson> lessons) {
        LessonListAdapter adapter = new LessonListAdapter(getContext() , lessons , mEmail , mUserType , mCourseCode) ;
        mLessonsList.setAdapter(adapter);
    }

    @Override
    public void navigateToNewLessonScreen() {
        Intent intent = new Intent(getContext() , NewLessonActivity.class);
        intent.putExtra("Email" , mEmail);
        intent.putExtra("CourseCode" , mCourseCode);
        startActivity(intent);
    }

    @Override
    public void navigateToLessonDetailsScreen() {

    }

    //region private functions
    void adjustLayout() {

        if(mUserType == false) {
            mAddLesson.setVisibility(View.GONE);
        }
    }

    //endregion

}

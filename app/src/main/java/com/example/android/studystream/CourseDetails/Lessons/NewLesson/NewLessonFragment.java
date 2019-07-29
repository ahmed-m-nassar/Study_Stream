package com.example.android.studystream.CourseDetails.Lessons.NewLesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.studystream.R;

public class NewLessonFragment extends Fragment implements NewLessonContract.View {

    private NewLessonPresenter  mPresenter;
    private EditText            mLessonTitle;
    private Button              mAddLesson;
    private String              mEmail;
    private int                 mCourseCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_lesson , container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        ///////////////////////////////////////////////////////////////

        //extras
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mEmail = bundle.getString("Email");
            mCourseCode = bundle.getInt("CourseCode");
        }
        //views
        mLessonTitle = (EditText)view.findViewById(R.id.NewLesson_Title_EditText);
        mAddLesson   = (Button)view.findViewById(R.id.NewLesson_NewLesson_Button);

        //presenter
        mPresenter = new NewLessonPresenter(this);

        ///////////////////////////////////////////////////////////////

        //setting click listener
        mAddLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addLessonButtonClicked(mLessonTitle.getText().toString() , mCourseCode ,mEmail);
            }
        });
    }



    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        getActivity().onBackPressed();
    }


}

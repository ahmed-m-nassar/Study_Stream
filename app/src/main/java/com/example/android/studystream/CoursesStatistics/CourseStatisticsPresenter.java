package com.example.android.studystream.CoursesStatistics;

import com.example.android.studystream.CoursesStatistics.Data.CourseStatisticsLocalServices;
import com.example.android.studystream.CoursesStatistics.Data.CourseStatisticsLocalServicesImpl;
import com.example.android.studystream.CoursesStatistics.Model.Statistics;

import java.util.ArrayList;

public class CourseStatisticsPresenter implements CourseStatisticsContract.Presenter {
    CourseStatisticsContract.View  mView;
    CourseStatisticsLocalServices  mModel;

    public CourseStatisticsPresenter(CourseStatisticsContract.View mView) {
        this.mView = mView;
        mModel = new CourseStatisticsLocalServicesImpl();
    }

    @Override
    public void getCoursesStatistics(String email) {
        ArrayList<Integer> ids = mModel.getCoursesIdByDoctor(email);
        ArrayList<Statistics> coursesStatistics = new ArrayList<>();
        for(int i = 0 ; i < ids.size() ; i ++){
            int lessonsNumber = mModel.GetNumberOfLessons(ids.get(i));
            int announcementsNumber = mModel.GetNumberOfAnnouncements(ids.get(i));
            int materialsNumber = mModel.GetNumberOfMaterials(ids.get(i));
            int questionNumber = mModel.GetNumberOfQuestions(ids.get(i));
            int answersNumber = mModel.GetNumberOfAnswers(ids.get(i));
            int studentsNumber = mModel.GetNumberOfStudents(ids.get(i));

            coursesStatistics.add(new Statistics(ids.get(i) , lessonsNumber,materialsNumber,announcementsNumber,
                    questionNumber,answersNumber ,studentsNumber));
        }

        mView.fillCoursesStatistics(coursesStatistics);
    }
}

package com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Adapter;

import android.view.View;

public interface MaterialListClickListeners {
    View.OnClickListener materialItemClicked(int materialNumber , String materialTitle , String materialContent);
}

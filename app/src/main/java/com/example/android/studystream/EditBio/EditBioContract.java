package com.example.android.studystream.EditBio;

public interface EditBioContract {
    interface View {
        void showMessage(String message);
        void finishScreen();
    }

    interface Presenter {
        void EditBioButtonClicked( String bio , String email);
    }
}

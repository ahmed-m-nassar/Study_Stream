package com.example.android.studystream.Invite;

import com.example.android.studystream.Invite.Model.Contract;

import java.util.ArrayList;

public interface InviteContract {
    interface View {
        void fillContractsList(ArrayList<Contract> contracts);
        boolean checkReadContactPermission();
        void requestReadContactPermission();

        void showMessage(String message);
        void finishScreen();
    }

    interface Presenter {
        void getContracts();
    }
}

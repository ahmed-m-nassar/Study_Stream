package com.example.android.studystream.Invite;

import com.example.android.studystream.Invite.Data.InviteServices;
import com.example.android.studystream.Invite.Data.InviteServicesImpl;
import com.example.android.studystream.Invite.Model.Contract;

import java.util.ArrayList;

public class InvitePresenter implements InviteContract.Presenter {
    private InviteContract.View  mView;
    private InviteServices       mModel;

    public InvitePresenter(InviteContract.View mView) {
        this.mView = mView;
        mModel = new InviteServicesImpl();
    }

    @Override
    public void getContracts() {
        if(!mView.checkReadContactPermission()) {
            mView.requestReadContactPermission();
            return;
        }

        ArrayList<Contract> contracts = mModel.getContracts();

        mView.fillContractsList(contracts);
    }
}

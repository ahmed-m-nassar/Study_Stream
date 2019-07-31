package com.example.android.studystream.Invite;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.studystream.Invite.Adapter.ContractClickListeners;
import com.example.android.studystream.Invite.Adapter.ContractListAdapter;
import com.example.android.studystream.Invite.Data.InviteServices;
import com.example.android.studystream.Invite.Model.Contract;
import com.example.android.studystream.R;

import java.util.ArrayList;
import java.util.List;


public class InviteFragment extends Fragment implements InviteContract.View , ContractClickListeners {
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private InvitePresenter mPresenter;
    private RecyclerView mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invite , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new InvitePresenter(this);
        mList = (RecyclerView) view.findViewById(R.id.Invite_List_ListView);

        mPresenter.getContracts();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                mPresenter.getContracts();
            } else {
                Toast.makeText(getContext(), "Until you grant the permission, we get your contacts", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void fillContractsList(ArrayList<Contract> contracts) {
        ContractListAdapter adapter = new ContractListAdapter(contracts,getContext() ,this);
        mList.setAdapter(adapter);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public boolean checkReadContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return  false;
        } else {
            return true;
        }

    }

    @Override
    public void requestReadContactPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishScreen() {
        getActivity().onBackPressed();
    }

    @Override
    public View.OnClickListener inviteButtonClickListener(String name, final String Number) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + Number));
                intent.putExtra("sms_body", "I invite you to use Study Stream!");
                startActivity(intent);
            }
        };
    }
}

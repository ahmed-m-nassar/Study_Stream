package com.example.android.studystream.Invite.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.studystream.CoursesStatistics.Model.Statistics;
import com.example.android.studystream.Invite.Model.Contract;
import com.example.android.studystream.R;

import java.util.ArrayList;
import java.util.List;

public class ContractListAdapter extends RecyclerView.Adapter<ContractListAdapter.ViewHolder> {

    public ArrayList<Contract> contracts = new ArrayList<Contract>();
    public Context mContext;
    public ContractClickListeners mClickListeners;
    public ContractListAdapter(ArrayList<Contract> contracts, Context mContext , ContractClickListeners clickListeners) {
        this.contracts = contracts;
        this.mContext = mContext;
        this.mClickListeners = clickListeners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.mName.setText(contracts.get(i).getName().toString());
        viewHolder.mNumber.setText(contracts.get(i).getPhone().toString());



        viewHolder.mInvite.setOnClickListener(mClickListeners.inviteButtonClickListener(contracts.get(i).getName(),
                contracts.get(i).getPhone()));
    }

    @Override
    public int getItemCount() {
        return contracts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView mName;
        TextView mNumber;
        Button mInvite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.ContactListItem_Name_TextView);
            mNumber = itemView.findViewById(R.id.ContactListItem_Phone_TextView);
            mInvite = itemView.findViewById(R.id.ContactListItem_invite_Button);
        }
    }
}
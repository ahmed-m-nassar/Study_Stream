package com.example.android.studystream.CourseDetails.Announcements.CourseAnnouncements.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.studystream.Answers.QuestionAnswers.Adapter.AnswerListItemClickListeners;
import com.example.android.studystream.CourseDetails.Announcements.Models.Announcement;
import com.example.android.studystream.R;

import java.util.List;

public class AnnouncementListAdapter extends ArrayAdapter<Announcement> {
    private boolean                        mUserType;
    private String                         mUserEmail;
    private Context                        mContext;
    private AnnouncementListClickListeners mClickListeners;
    public AnnouncementListAdapter(@NonNull Context context, List<Announcement> announcements
                                  , String email , boolean userType ,AnnouncementListClickListeners clickListeners) {
        super(context, 0,announcements);
        mUserType = userType;
        mUserEmail = email;
        mContext = context;
        mClickListeners = clickListeners;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Announcement Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.announcement_list_item,parent,false);
        }

        TextView announcementTitle = (TextView) ListItemView.findViewById(R.id.AnnouncementListItem_Title_TextView);
        TextView announcementContent = (TextView) ListItemView.findViewById(R.id.AnnouncementListItem_Content_TextView);

        announcementTitle.setText(Item.getmTitle().toString());
        announcementContent.setText(Item.getmContent().toString());

        //setting click listener
       if(mUserType == true) { //if the user is a doctor he can edit the announcement
           LinearLayout announcementParent = (LinearLayout)ListItemView.findViewById(R.id.AnnouncementListItem_Parent_Layout);
           announcementParent.setOnClickListener(mClickListeners.announcementClicked(Item.getmAnnouncementNum()
                                                                                    ,Item.getmTitle()
                                                                                    ,Item.getmContent()));


       }

        return ListItemView;
    }

}

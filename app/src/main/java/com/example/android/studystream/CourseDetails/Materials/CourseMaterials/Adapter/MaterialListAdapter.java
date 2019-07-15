package com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Adapter;

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

import com.example.android.studystream.CourseDetails.Materials.CourseMaterials.Data.Models.Material;
import com.example.android.studystream.CourseDetails.Materials.EditMaterial.EditMaterialActivity;
import com.example.android.studystream.R;

import java.util.List;

public class MaterialListAdapter extends ArrayAdapter<Material> {
    private boolean mUserType;
    private String  mUserEmail;
    private Context mContext;
    public MaterialListAdapter(@NonNull Context context, List<Material> materials , String email , boolean userType) {
        super(context, 0,materials);
        mUserType = userType;
        mUserEmail = email;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Material Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.material_list_item,parent,false);
        }

        TextView materialTitle = (TextView) ListItemView.findViewById(R.id.MaterialListItem_MaterialTitle_TextView);
        TextView materialContent = (TextView) ListItemView.findViewById(R.id.MaterialListItem_MaterialContent_TextView);

        materialTitle.setText(Item.getmTitle().toString());
        materialContent.setText(Item.getmContent().toString());

        //setting click listener
        if(mUserType == true) { //if the user is a doctor he can edit the material
            LinearLayout materialParent = (LinearLayout)ListItemView.findViewById(R.id.MaterialListItem_Parent_Layout);
            materialParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext , EditMaterialActivity.class);
                    intent.putExtra("CourseCode" , Item.getmCourseCode() );
                    intent.putExtra("MaterialNumber" , Item.getmMaterialNum());
                    intent.putExtra("MaterialTitle" , Item.getmTitle());
                    intent.putExtra("MaterialContent" , Item.getmContent());
                    intent.putExtra("Email" , mUserEmail);

                    mContext.startActivity(intent);
                }
            });

        }


        return ListItemView;
    }
}

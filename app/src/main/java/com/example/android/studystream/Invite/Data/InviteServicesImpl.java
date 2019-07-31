package com.example.android.studystream.Invite.Data;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.android.studystream.Base.DataBase.MyApp;
import com.example.android.studystream.Invite.Model.Contract;

import java.util.ArrayList;

public class InviteServicesImpl implements InviteServices {

    private Context mContext;
    public InviteServicesImpl() {
        mContext = MyApp.getAppContext();
    }

    @Override
    public ArrayList<Contract> getContracts() {
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);

        ArrayList<Contract> contracts = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                int hasPhone = Integer.valueOf(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhone == 1) {
                    Cursor numbers = mContext.getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null);
                    while (numbers.moveToNext()) {
                        String number = numbers.getString(numbers.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contracts.add(new Contract(name , number));
                    }
                    numbers.close();
                }


            }
        }

        return contracts;
    }
}


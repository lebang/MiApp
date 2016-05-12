package com.miapp.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miapp.R;
import com.miapp.Widget.UIListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebang on 16-5-11.
 */
public class ContactsActivity extends BaseActivity {

    private static final String TAG = "ContactsActivity";
    private UIListView mContactsView;
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> mContactsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mContactsView = (UIListView)findViewById(R.id.contacts_view);
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContactsList);
        mContactsView.setAdapter(mArrayAdapter);
        readContacts();
    }

    private void readContacts(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mContactsList.add(displayName + "\n" + number);
                Log.d(TAG, "readContacts: " + displayName + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }
}

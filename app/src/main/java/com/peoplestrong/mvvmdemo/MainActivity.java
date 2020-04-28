package com.peoplestrong.mvvmdemo;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.peoplestrong.mvvmdemo.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//background serveices contet read show phone no and emai id and name. store in DB
//show only mobile no not land line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchContacts().execute();
    }
    class FetchContacts extends AsyncTask<Void, Void, ArrayList<Contact>> {

        private final String DISPLAY_NAME = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME;

        private final String FILTER = DISPLAY_NAME + " NOT LIKE '%@%'";

        private final String ORDER = String.format("%1$s COLLATE NOCASE", DISPLAY_NAME);

        @SuppressLint("InlinedApi")
        private final String[] PROJECTION = {
                ContactsContract.Contacts._ID,
                DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };
        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {
            try {
                ArrayList<Contact> contacts = new ArrayList<Contact>();

                ContentResolver cr =getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, FILTER, null, ORDER);
                if (cursor != null && cursor.moveToFirst()) {

                    do {
                        // get the contact's information
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                        Integer hasPhone = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        // get the user's email address
                        String email = null;
                        Cursor ce = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                        if (ce != null && ce.moveToFirst()) {
                            email = ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                            ce.close();
                        }

                        // get the user's phone number
                        String phone = null;
                        if (hasPhone > 0) {
                            Cursor cp = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                            if (cp != null && cp.moveToFirst()) {
                                phone = cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                cp.close();
                            }
                        }

                        // if the user user has an email or phone then add it to contacts
                       // if ((!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                         //       && !email.equalsIgnoreCase(name)) || (!TextUtils.isEmpty(phone))) {
                            Contact contact = new Contact();
                            contact.name = name;
                            contact.email = email;
                            contact.phoneNumber = phone;
                            contacts.add(contact);
                      //  }

                    } while (cursor.moveToNext());

                    // clean up cursor
                    cursor.close();
                }

                return contacts;
            } catch (Exception ex) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            if (contacts != null) {
                // success
                ArrayList<Contact> mContacts=new ArrayList<>();
                mContacts = contacts;
                for (int i = 0; i <mContacts.size() ; i++) {
                    Log.d("name",mContacts.get(i).name);
                    Log.d("email",mContacts.get(i).email);

                }
            } else {
                // show failure
                // syncFailed();
            }
        }
    }

}

package de.ub0r.android.smsdroid;

import android.database.Cursor;
import android.net.Uri;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrew on 4/16/16.
 */
public class SmsParser extends AppCompatActivity{

//    public static final String inbox = "content://sms/inbox";
//
//
//    public static void logger (String message, String location) throws IOException {
//        PrintWriter out = new PrintWriter(new FileWriter(location, true), true);
//        out.write(message);
//        out.close();
//        Log.i("Text", "writing succcessful");
//    }
//
//    public void parseInbox () throws IOException {
//        //String location = "content://sms/inbox";
//        Uri uriSMSURI = Uri.parse("content://sms/inbox");
//        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,null);
//        System.out.print("hello" + cur.getColumnNames());
//        String sms = "";
//        while (cur.moveToNext()) {
//            sms += "From :" + cur.getString(cur.getColumnIndexOrThrow("address")).toString() + " : " + cur.getString(cur.getColumnIndexOrThrow("body")).toString()+"\n";
//        }
//
//        logger(sms, "inbox.txt");
//    }




}

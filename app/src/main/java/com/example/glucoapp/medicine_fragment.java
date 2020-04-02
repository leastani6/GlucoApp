package com.example.glucoapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.glucoapp.Activity2.MY_PREFS_NAME;


public class medicine_fragment extends Fragment {
    EditText name, time;
    Button save;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
        View v = inflater.inflate(R.layout.fragmet_medicine, container, false);
TextView text=v.findViewById(R.id.shfaq);
        ListView listView = v.findViewById(R.id.listview);
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        System.out.println(name);

        DatabaseHelper myDB = new DatabaseHelper(getActivity());
        ArrayList<HashMap<String, String>> userList = myDB.GetMeds(name);
//ArrayAdapter<String> adapter=new ArrayAdapter<String>();
        if(userList.isEmpty()) {text.setText("ju lutem shtoni te dhena");}
        else {
            ListAdapter adapter = new SimpleAdapter(getActivity(), userList, R.layout.row_layout, new String[]{"name", "time"}, new int[]{R.id.lol, R.id.ora});


            listView.setAdapter(adapter);
            System.out.println(userList);
        }

        FloatingActionButton fab = v.findViewById(R.id.floating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });


        final FloatingActionButton notify = v.findViewById(R.id.notify);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

      AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Njoftime");
                builder.setMessage("Doni te aktivizoni njoftimet?");
                builder.setPositiveButton("Po",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //if you are on `MainActivity`
//                                /startAlarm(true,true);
                                Toast.makeText(getContext(),
                                        "ok", Toast.LENGTH_SHORT).show();
                                NotificationCompat.Builder mBuilder =
                                        new NotificationCompat.Builder(getContext())
                                                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                                .setContentTitle("Beni insulinen!")
                                                .setContentText("Beni  insukinen e ores 20:00");
                                NotificationManager mNotificationManager =

                                        (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            //    NotificationManager.notify().

                                        mNotificationManager.notify(001, mBuilder.build());
                            }
                        });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                android.R.string.no, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }

        });
        return v;
    }


    private void displayDialog() {
        SharedPreferences prefs =getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final  String name1 = prefs.getString("name", "No name defined");



        Dialog d = new Dialog(getActivity());
        d.setTitle("Shto");
        d.setContentView(R.layout.dialog_layout);

        name = (EditText) d.findViewById(R.id.med_name);
        time = (EditText) d.findViewById(R.id.med_time);
        save = (Button) d.findViewById(R.id.save);
        //  DatabaseHelper   db=new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String m = time.getText().toString();
                DatabaseHelper db = new DatabaseHelper(getContext());
                Long res = db.addmed(m, n, name1);
                if (res > 0) {
                    Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        d.show();
    }
}
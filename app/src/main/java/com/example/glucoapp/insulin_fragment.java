package com.example.glucoapp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static com.example.glucoapp.Activity2.MY_PREFS_NAME;

public class insulin_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
               View v = inflater.inflate(R.layout.fragmet_insuline, container, false);
        TextView text=v.findViewById(R.id.shfaq1);
        ListView listView = v.findViewById(R.id.listview1);

        SharedPreferences prefs =getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name1 = prefs.getString("name", "No name defined");

        DatabaseHelper myDB = new DatabaseHelper(getActivity());
        ArrayList<HashMap<String, String>> userList = myDB.GetIns(name1);
//ArrayAdapter<String> adapter=new ArrayAdapter<String>();
        if(userList.isEmpty()) {text.setText("ju lutem shtoni te dhena");}
        else {

            ListAdapter adapter = new SimpleAdapter(getActivity(), userList, R.layout.row_layout1, new String[]{"name", "time"}, new int[]{R.id.ins, R.id.ora1});


            listView.setAdapter(adapter);
            System.out.println(userList);

        }

        FloatingActionButton fab = v.findViewById(R.id.floating1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
        return v;
    }


    private void displayDialog() {

        Dialog d = new Dialog(getActivity());
        d.setTitle("Shto");
        d.setContentView(R.layout.dialog_layout);

        final EditText name = (EditText) d.findViewById(R.id.med_name);
        final EditText time = (EditText) d.findViewById(R.id.med_time);
        Button save = (Button) d.findViewById(R.id.save);
        //  DatabaseHelper   db=new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String m = time.getText().toString();
                DatabaseHelper db = new DatabaseHelper(getContext());
                SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String name = prefs.getString("name", "No name defined");
                System.out.println(name);
                SharedPreferences prefs1 = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String name1 = prefs1.getString("name", "No name defined");//"No name defined" is the default value.
                int idName = prefs1.getInt("idName", 0);
                System.out.println(name1);
                Long res = db.addins(m, n,name1);
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


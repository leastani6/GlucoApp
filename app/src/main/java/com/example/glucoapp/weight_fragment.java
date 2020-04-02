package com.example.glucoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class weight_fragment extends Fragment {
    EditText pesha, gjatesia;
    Button calculate;
    TextView shfaq;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_weight, container, false);
        final EditText pesha = v.findViewById(R.id.weight);
        final EditText gjatesia = v.findViewById(R.id.height);
        final TextView text = v.findViewById(R.id.output);
        final TextView rezultat = v.findViewById(R.id.Res);
        final Button shfaq = v.findViewById(R.id.llograrit);
        shfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = pesha.getText().toString();
                String h = gjatesia.getText().toString();
                final int pesha1 = Integer.parseInt(p);
                final float gjatesia1 = Float.parseFloat(h);
                float quadratic = gjatesia1 * gjatesia1;
                float llogaritje = pesha1 / quadratic;
                if (llogaritje <= 19) {
                    rezultat.setText("Nenpeshe");
                } else if (19 < llogaritje && llogaritje < 24) {
                    rezultat.setText("Normal");
                } else {
                    rezultat.setText("Mbipeshe");
                }


                String l = Float.toString(llogaritje);
                text.setText(l);
                System.out.println(l);
            }
        });

        return v;
    }
}

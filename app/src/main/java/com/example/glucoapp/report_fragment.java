package com.example.glucoapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.itextpdf.text.Annotation.FILE;
import static com.itextpdf.text.Annotation.PAGE;

public class report_fragment extends Fragment {

    Button raport;
    Button email;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_report, container, false);

        Button raport=v.findViewById(R.id.button5);
        Button email=v.findViewById(R.id.button6);

        raport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Raporti eshte ruajtur", Toast.LENGTH_SHORT).show();
                firstpdf1 pdf=new firstpdf1(getContext());
                pdf.create();

            }

        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  createPdf("lea");
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                builder.detectFileUriExposure();
                Toast.makeText(getActivity(), "Emaili u dergua", Toast.LENGTH_SHORT).show();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("application/pdf");
                shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "leastani6@gmail.com" });
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, " Glucapp Report ");
               // shareIntent.putExtra(Intent.EXTRA_TEXT, "test");
                File file=new File(Environment.getExternalStorageDirectory().getPath() +"/mypdf/report.pdf");
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                startActivity(shareIntent);
            }
        });



        return v;
    }}


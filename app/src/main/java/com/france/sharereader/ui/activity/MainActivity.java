package com.france.sharereader.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.france.sharereader.R;
import com.joanzapata.pdfview.PDFView;

public class MainActivity extends Activity  {

    public static final String SAMPLE_FILE = "sample.pdf";

    public static final String ABOUT_FILE = "about.pdf";
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        PDFView pdfView= (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("sample.pdf")
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }


}
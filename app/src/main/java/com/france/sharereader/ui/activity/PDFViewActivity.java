package com.france.sharereader.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.france.sharereader.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

/**
 * Created by Administrator on 2015/11/13.
 */
public class PDFViewActivity extends BaseActivity implements OnPageChangeListener {

    public static final String SAMPLE_FILE = "sample.pdf";

    public static final String ABOUT_FILE = "about.pdf";

    PDFView pdfView;

    String pdfName = SAMPLE_FILE;

    Integer pageNumber = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        pdfView=(PDFView)findViewById(R.id.pdfView);
        Intent intent=getIntent();
        String fileName=intent.getStringExtra("fileName");
        String filePath=intent.getStringExtra("filePath");
        display(fileName,filePath,true);
    }
    private void display(String fileName,String filePath, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = fileName);

//        pdfView.fromAsset(assetFileName)
//                .defaultPage(pageNumber)
//                .onPageChange(this)
//                .load();
        File file = new File(filePath);
        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfName, page, pageCount));
    }

    @Override
    public void onBackPressed() {
//        if (ABOUT_FILE.equals(pdfName)) {
//            display(SAMPLE_FILE, true);
//        } else {
            super.onBackPressed();
//        }
    }

    private boolean displaying(String fileName) {
        return fileName.equals(pdfName);
    }
}
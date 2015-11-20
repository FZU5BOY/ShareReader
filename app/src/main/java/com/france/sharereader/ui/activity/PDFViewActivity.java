package com.france.sharereader.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.france.sharereader.R;
import com.france.sharereader.bean.Book;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2015/11/13.
 */
public class PDFViewActivity extends BaseActivity implements OnPageChangeListener {

    public static final String SAMPLE_FILE = "sample.pdf";

    public static final String ABOUT_FILE = "about.pdf";

    PDFView pdfView;

    String pdfName = SAMPLE_FILE;

    Integer pageNumber = 1;
    Book book=new Book();
    Integer pageCount = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        pdfView=(PDFView)findViewById(R.id.pdfView);
        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            book=(Book)bundle.getSerializable("pdf");
            String fileName=book.getBookName();
            pageNumber=book.getCurrentPage();
            String filePath=book.getLocalPath();
            display(fileName,filePath,false);
        }

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
        this.pageCount = pageCount;
        setTitle(String.format("%s %s / %s", pdfName, page, pageCount));
        ShowLog(String.format("%s %s / %s", pdfName, page, pageCount));
    }

//    @Override
//    public void onBackPressed() {
////        if (ABOUT_FILE.equals(pdfName)) {
////            display(SAMPLE_FILE, true);
////        } else {
//            super.onBackPressed();
////        }
//    }

    private boolean displaying(String fileName) {
        return fileName.equals(pdfName);
    }
    @Override
    public void onBackPressed() {
        book.setCurrentPage(pageNumber);
        book.setProgress((int) (100 * ((double) pageNumber + 0.1) / (double) pageCount));
        baseDaoImpl.update(book);
        ShowLog("bookID"+book.getBookId()+" after update page:"+book.getCurrentPage());
        Intent intent =new Intent(PDFViewActivity.this,MainActivity.class);
        startActivity(intent);
        PDFViewActivity.this.finish();
    }
}
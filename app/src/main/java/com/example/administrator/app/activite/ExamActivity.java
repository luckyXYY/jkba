package com.example.administrator.app.activite;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ExamApplication;
import com.example.administrator.app.R;
import com.example.administrator.bean.Exam;
import com.example.administrator.bean.ExamInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {

    TextView tvExamInfo,tvExamTitle,tvop1,tvop2,tvop3,tvop4;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        initData();
    }
   private void initView() {

       tvExamInfo=(TextView) findViewById(R.id.tv_examinfo);
       tvExamInfo=(TextView) findViewById(R.id.tv_exam_title);
       tvExamInfo=(TextView) findViewById(R.id.tv_op1);
       tvExamInfo=(TextView) findViewById(R.id.tv_op2);
       tvExamInfo=(TextView) findViewById(R.id.tv_op3);
       tvExamInfo=(TextView) findViewById(R.id.tv_op4);
       mImageView=(ImageView) findViewById(R.id.tv_exam_image);
   }

    private void initData(){
       ExamInfo examInfo = ExamApplication.getInstance().getExamInfo();
        //Log.e("hei","wentishi"+examInfo);
        if(examInfo!=null){
            showData(examInfo);
        }
        List<Exam> examList=ExamApplication.getInstance().getExamList();
        if(examList!=null){
            showExam(examList);
        }
    }
    private void showExam(List<Exam> examList) {
        Exam exam=examList.get(0);
        if(exam!=null){
            tvExamTitle.setText(exam.getQuestion());
            tvop1.setText(exam.getItem1());
            tvop2.setText(exam.getItem2());
            tvop3.setText(exam.getItem3());
            tvop4.setText(exam.getItem4());
            Picasso.with(ExamActivity.this)
                    .load(exam.getUrl())
                    .into(mImageView);
        }
    }
    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());
    }
}

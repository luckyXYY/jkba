package com.example.administrator;

import android.app.Application;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.administrator.bean.Exam;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Result;
import com.example.administrator.biz.ExamBiz;
import com.example.administrator.biz.IExamBiz;
import com.example.administrator.utils.OkHttpUtils;
import com.example.administrator.utils.ResultUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ExamApplication extends Application {
    public  static String LOAD_EXAM_INFO="load_exam_info";
    public  static String LOAD_EXAM_QUESTION="load_exam_question";
    public  static String LOAD_DATA_SUCCESS="load_data_success";
    ExamInfo mExamInfo;
    List<Exam> mExamList;
    private static ExamApplication instance;
    IExamBiz biz;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        biz=new ExamBiz();
      //  mExamInfo = new ExamInfo();
    }

    public static ExamApplication getInstance() {
        return instance;
    }

    public ExamInfo getExamInfo() {
        return mExamInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        mExamInfo = examInfo;
    }

    public List<Exam> getExamList() {
        return mExamList;
    }

    public void setExamList(List<Exam> mExamList) {
        this.mExamList = mExamList;
    }
}
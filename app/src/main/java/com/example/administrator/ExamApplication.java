package com.example.administrator;

import android.app.Application;
import android.util.Log;

import com.example.administrator.bean.Exam;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ExamApplication extends Application {

    ExamInfo mExamInfo;
    List<Exam> mExamList;
    private static ExamApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mExamInfo = new ExamInfo();
        initData();
    }

    public static ExamApplication getInstance() {
        return instance;
    }

    private void initData() {
        OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(instance);
        String uri="http://101.251.196.90:8080/JztkServer/examInfo";
        utils.url(uri)
                .targetClass(ExamInfo.class)
                .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>()
                {
                    @Override
                    public void onSuccess(ExamInfo result) {

                        Log.e("main","result="+result);
                        mExamInfo=result;
                    }
                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);
                    }
                });
    }

    public ExamInfo getExamInfo() {
        return mExamInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        mExamInfo = examInfo;
    }

}
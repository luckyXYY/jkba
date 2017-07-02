package com.example.administrator;

import android.app.Application;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.administrator.bean.Exam;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Result;
import com.example.administrator.utils.OkHttpUtils;
import com.example.administrator.utils.ResultUtils;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
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

                OkHttpUtils<String> utils1=new OkHttpUtils<>(instance);
                String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
                utils1.url(url2);
                utils1.targetClass(String.class)
                    .execute(new OkHttpUtils.OnCompleteListener<String>() {
                        @Override
                        public void onSuccess(String jsonStr) {
                            Result result= ResultUtils.getListResultFromJson(jsonStr);
                            if(result!=null && result.getError_code()==0){
                                List<Exam> list=result.getResult();
                                if(list!=null && list.size()>0){
                                    mExamList=list;
                                }
                            }
                        }

                        @Override
                        public void onError(String error) {
                            Log.e("main","error="+error);

                        }
                    });


            }
        }).start();
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
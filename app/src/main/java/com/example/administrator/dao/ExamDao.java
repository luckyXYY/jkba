package com.example.administrator.dao;

import android.util.Log;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.Exam;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Result;
import com.example.administrator.utils.OkHttpUtils;
import com.example.administrator.utils.ResultUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExamDao implements IExamDao{
    @Override
    public void loadExamInfo() {
        OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(ExamApplication.getInstance());
        String uri="http://101.251.196.90:8080/JztkServer/examInfo";
        utils.url(uri)
                .targetClass(ExamInfo.class)
                .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>()
                {
                    @Override
                    public void onSuccess(ExamInfo result) {

                        Log.e("main","result="+result);

                        ExamApplication.getInstance().setExamInfo(result);
                    }
                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);
                    }
                });
        }


    @Override
    public void loadQuestionLists() {
        OkHttpUtils<String> utils1=new OkHttpUtils<>(ExamApplication.getInstance());
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
                                ExamApplication.getInstance().setExamList(list);
                                //mExamList=list;
                            }
                        }
                    }
                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);
                    }
                });
                }
}

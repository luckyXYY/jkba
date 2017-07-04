package com.example.administrator.biz;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.Exam;
import com.example.administrator.dao.ExamDao;
import com.example.administrator.dao.IExamDao;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExamBiz implements IExamBiz {
    IExamDao dao;
    int examIndex=0;
    List<Exam>examList=null;
    public ExamBiz(){
        this.dao=new ExamDao();
    }

    @Override
    public void beginExam() {
        examIndex=0;
        dao.loadExamInfo();
        dao.loadQuestionLists();
        //examList=ExamApplication.getInstance().getExamList();
    }
    @Override
    public Exam getExam() {
        examList=ExamApplication.getInstance().getExamList();
        if(examList!=null){
            return examList.get(examIndex);
        }else {
            return null;
        }
    }

    @Override
    public Exam  nextQuestion() {
        if(examList!=null && examIndex<examList.size()-1){
            examIndex++;
            return examList.get(examIndex);

        }else {
            return null;
        }
    }
    @Override
    public Exam preQuestion() {
        if(examList!=null && examIndex>0){
            examIndex--;
            return examList.get(examIndex);
        }else {
            return null;
        }
    }

    @Override
    public int commitExam() {
        int s=0;
        for(Exam exam:examList){
            String userAnswer=exam.getUserAnswer();
            if(userAnswer!=null && !userAnswer.equals("")){
                if(exam.getAnswer().equals(userAnswer)){
                    s++;
                }
            }
        }

        return s;

    }

    @Override
    public String getExamIndex() {
        return (examIndex+1)+".";
    }
}

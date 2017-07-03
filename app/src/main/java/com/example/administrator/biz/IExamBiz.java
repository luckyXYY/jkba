package com.example.administrator.biz;
import com.example.administrator.bean.Exam;

/**
 * Created by Administrator on 2017/7/3.
 */

public interface IExamBiz {
    void beginExam();
    Exam getExam();
    Exam nextQuestion();
    Exam preQuestion();
    void commitExam();
    String getExamIndex();
}

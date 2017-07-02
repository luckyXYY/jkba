package com.example.administrator.bean;

import java.util.List;



/**
 * Created by Administrator on 2017/6/29.
 */

public class Result {


    /**
     * error_code : 0
     * reason : ok
     * result : [{"id":34,"question":"这个标志是何含义？","answer":"2","item1":"直行和向左转弯","item2":"直行和向右转弯","item3":"禁止直行和向右转弯","item4":"只准向左和向右转弯","explains":"直行和向右转弯：表示只准一切车辆直行和向右转弯。此标志设在车辆必须直行和向右转弯）的路口以前适当位置。","url":"http://images.juheapi.com/jztk/c1c2subject1/34.jpg"}]
     */

    private int error_code;
    private String reason;
    private List<Exam> result;

    public void setResult(List<Exam> result) {
        this.result = result;
    }


    public List<Exam> getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
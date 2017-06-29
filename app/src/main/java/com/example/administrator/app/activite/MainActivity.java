package com.example.administrator.app.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.app.R;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.utils.OkHttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(getApplication());
        String uri="http://101.251.196.90:8080/JztkServer/examInfo\n";
        utils.url(uri)
                .targetClass(ExamInfo.class)
                .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>()
                {
                    @Override
                    public void onSuccess(ExamInfo result) {
                        Log.e("main","result="+result);
                    }
                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);

                    }
                });
        startActivity(new Intent(MainActivity.this,ExamActivity.class));
    }
    public void exit(View view) {
        finish();
    }
}

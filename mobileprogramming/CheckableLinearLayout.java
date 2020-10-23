package com.example.mobileprogramming;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;


public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    //checkable 인터페이스 abstract함수 override해야함

    public boolean isChecked(){ //현재 checked 상태 확인
        CheckBox checkBox = findViewById(R.id.checkbox);
        return checkBox.isChecked();
    }

    public void toggle(){ //현재 checked 상태 변경
        CheckBox checkBox = findViewById(R.id.checkbox);
        setChecked(!checkBox.isChecked());
    }

    public void setChecked(boolean checked){ //checked 상태를 checked 변수대로 설정
        CheckBox checkBox = findViewById(R.id.checkbox);
        if(checkBox.isChecked() != checked){
            checkBox.setChecked(checked);
        }
    }
}

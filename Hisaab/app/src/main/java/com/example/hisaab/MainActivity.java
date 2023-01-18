package com.example.hisaab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv, solutionTv;
    private MaterialButton buttonC, buttonOpen,buttonClose, buttonDivide, button7, button8, button9, button1,
            button2, button3, button4, button5,button6,button0, buttonDot,buttonAC, buttonEqual, buttonMinus,
            buttonPlus, buttonMul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        buttonC= findViewById(R.id.button_c);
        buttonOpen= findViewById(R.id.button_open);
        buttonClose= findViewById(R.id.button_close);
        buttonDivide= findViewById(R.id.button_divide);
        button7= findViewById(R.id.button_7);
        button6= findViewById(R.id.button_6);
        button5= findViewById(R.id.button_5);
        button4= findViewById(R.id.button_4);
        button3= findViewById(R.id.button_3);
        button2= findViewById(R.id.button_2);
        button1= findViewById(R.id.button_1);
        button0= findViewById(R.id.button_0);
        buttonDot= findViewById(R.id.button_Dot);
        button8= findViewById(R.id.button_8);
        button9= findViewById(R.id.button_9);
        buttonAC= findViewById(R.id.button_AC);
        buttonEqual = findViewById(R.id.button_Equal);
        buttonMinus= findViewById(R.id.button_minus);
        buttonPlus= findViewById(R.id.button_add);
        buttonMul= findViewById(R.id.button_mul);

    }
}
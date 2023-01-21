package com.example.hisaab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv, solutionTv;
    private MaterialButton buttonC, buttonOpen,buttonClose, buttonDivide, button7, button8, button9,
            button1,
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




        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonC.getText().toString();
                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);


            }
        });

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonOpen.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);


            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonClose.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);


            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonDivide.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);


            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button7.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button8.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button9.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button4.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button5.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button6.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button2.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button2.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button3.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button0.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonDot.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });


        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonAC.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonMul.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });


        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonPlus.getText().toString();

                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = buttonMinus.getText().toString();
                String data = solutionTv.getText().toString();
                data = data + s;
                solutionTv.setText(data);

            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solutionTv.setText("0");
                resultTv.setText("0");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = solutionTv.getText().toString();
                resultTv.setText(getResult(ans));
            }
        });


    }



    String getResult(String ans){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String FinalResult = context.evaluateString(scriptable,ans,"Javascript",1,null).toString();
            return FinalResult;
        }catch (Exception e){
            return "ERR";
        }

    }


}
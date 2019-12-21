package com.ntmhien.ailatrieuphu.dialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntmhien.ailatrieuphu.R;

public class GoiChoNguoiThan extends Dialog {
    private ImageButton btnHelpCall[];
    private TextView tvAnswer;
    private LinearLayout answerLayout, callLayout[];
    private RelativeLayout callsLayout;

    private String trueAnswer;

    public GoiChoNguoiThan(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.activity_goi_cho_nguoi_than);

        tvAnswer = (TextView) findViewById(R.id.tv_answer);

        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        findViewById(R.id.btn_close).setVisibility(View.VISIBLE);
    }
    public void setTrueAnswer(int trueAnswer) {
        if (trueAnswer == 1) {
            this.trueAnswer = "A";
        } else if (trueAnswer == 2) {
            this.trueAnswer = "B";
        } else if (trueAnswer == 3) {
            this.trueAnswer = "C";
        } else {
            this.trueAnswer = "D";
        }
        tvAnswer.setText("Theo tôi đáp án đúng là " + this.trueAnswer);
    }
}

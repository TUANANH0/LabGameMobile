package com.example.labgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2, sb3;
    ImageButton ibtnStart;
    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        txtDiem.setText(soDiem + "");
        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);

        // Thời gian cho cuộc đua
        final CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
            int number = 5;
            Random rd = new Random();
            int one = rd.nextInt(number);
            int two = rd.nextInt(number);
            int three = rd.nextInt(number);

            // Kết thúc cuộc đua khi có player chiến thắng
            if(sb1.getProgress() >= sb1.getMax()){
                this.cancel(); // máy ảo 5. trở lên thì this.cancel() được
                ibtnStart.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Chó Win",Toast.LENGTH_SHORT).show();
                if(cb1.isChecked()){
                    soDiem += 10;
                    Toast.makeText(MainActivity.this, "Bạn đã đoán đúng, bạn được cộng 10 điểm",Toast.LENGTH_SHORT).show();
                }else {
                    soDiem -= 10;
                    Toast.makeText(MainActivity.this, "Bạn đã đoán sai, bạn bị trừ 10 điểm",Toast.LENGTH_SHORT).show();
                }txtDiem.setText(soDiem +"");
                EnableCheckBox();
            }
            if(sb2.getProgress() >= sb2.getMax()){
                this.cancel();
                ibtnStart.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Thỏ Win",Toast.LENGTH_SHORT).show();
                if(cb2.isChecked()){
                    soDiem += 10;
                    Toast.makeText(MainActivity.this, "Bạn đã đoán đúng, bạn được cộng 10 điểm",Toast.LENGTH_SHORT).show();
                }else {
                    soDiem -= 10;
                    Toast.makeText(MainActivity.this, "Bạn đã đoán sai, bạn bị trừ 10 điểm",Toast.LENGTH_SHORT).show();
                }txtDiem.setText(soDiem +"");
                EnableCheckBox();
            }
            if(sb3.getProgress() >= sb3.getMax()){
                this.cancel();
                ibtnStart.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Cáo Win",Toast.LENGTH_SHORT).show();
                if(cb3.isChecked()){
                    soDiem += 10;
                    Toast.makeText(MainActivity.this, "Bạn đã đoán đúng, bạn được cộng 10 điểm",Toast.LENGTH_SHORT).show();
                }else {
                    soDiem -= 10;
                    Toast.makeText(MainActivity.this, "Bạn đã đoán sai, bạn bị trừ 10 điểm",Toast.LENGTH_SHORT).show();
                }txtDiem.setText(soDiem +"");
                EnableCheckBox();
            }

            //
            sb1.setProgress(sb1.getProgress() + one);
            sb2.setProgress(sb2.getProgress() + two);
            sb3.setProgress(sb3.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        // Bắt sự kiện cho ibtnStart
        ibtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    sb1.setProgress(0); // Mỗi khi nhấn nút start thì set progress về 0
                    sb2.setProgress(0);
                    sb3.setProgress(0);

                    ibtnStart.setVisibility(View.INVISIBLE); // Ẩn khi nhấn
                    countDownTimer.start();
                    DisableCheckBox();
                }else{
                    Toast.makeText(MainActivity.this, "Hãy dự đoán",Toast.LENGTH_SHORT).show();
                }

            }
        });


        // Nếu check 1 cb thì vô hiệu hóa check của những cb còn lại
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    // bỏ check 2 cb còn lại
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    // bỏ check 2 cb còn lại
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    // bỏ check 2 cb còn lại
                    cb2.setChecked(false);
                    cb1.setChecked(false);
                }
            }
        });


    }

    // Ánh xạ
    private void mapping(){
        txtDiem = (TextView) findViewById(R.id.txtDiem);
        cb1= (CheckBox) findViewById(R.id.cb1);
        cb2= (CheckBox) findViewById(R.id.cb2);
        cb3= (CheckBox) findViewById(R.id.cb3);
        sb1= (SeekBar) findViewById(R.id.sb1);
        sb2= (SeekBar) findViewById(R.id.sb2);
        sb3= (SeekBar) findViewById(R.id.sb3);
        ibtnStart= (ImageButton) findViewById(R.id.ibtnStart);

    }
    // Cho phép tick Cb
    private void EnableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    // Không cho phép tick Cb
    private void DisableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }
}

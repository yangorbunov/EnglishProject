//package com.trpp.englishproject.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//import com.trpp.englishproject.R;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//
//public class AddingActivity extends AppCompatActivity {
//
//    RadioGroup rg;
//    RadioButton rbText, rbTest, rbImage;
//    Button buttonShare;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adding);
//
//        rg = findViewById(R.id.rg_choose);
//        rbText = findViewById(R.id.radio_ch_1);
//        rbTest = findViewById(R.id.radio_ch_2);
//        rbImage = findViewById(R.id.radio_ch_3);
//
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(rbText.isChecked()){
//
//                }
//                if (rbTest.isChecked()){
//
//                }
//                if (rbImage.isChecked()){
//
//                }
//            }
//        });
//
//    }
//}
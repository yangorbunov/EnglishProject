package com.trpp.englishproject.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import com.trpp.englishproject.R;
import com.trpp.englishproject.View.Fragments.AddingImageFragment;
import com.trpp.englishproject.View.Fragments.AddingTestFragment;
import com.trpp.englishproject.View.Fragments.AddingTextFragment;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddingActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rbText, rbTest, rbImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        rg = findViewById(R.id.rg_choose);
        rbText = findViewById(R.id.radio_ch_1);
        rbTest = findViewById(R.id.radio_ch_2);
        rbImage = findViewById(R.id.radio_ch_3);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_ch, AddingTextFragment.newInstance())
                .commit();

        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            if(rbText.isChecked()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_ch, AddingTextFragment.newInstance())
                        .commit();
            }
            if (rbTest.isChecked()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_ch, AddingTestFragment.newInstance())
                        .commit();
            }
            if (rbImage.isChecked()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_ch, AddingImageFragment.newInstance())
                        .commit();
            }
        });

    }
}
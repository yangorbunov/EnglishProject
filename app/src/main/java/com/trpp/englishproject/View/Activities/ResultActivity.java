package com.trpp.englishproject.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.trpp.englishproject.ViewModel.VM;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import com.trpp.englishproject.R;

public class ResultActivity extends AppCompatActivity {

    TextView textView, mistakesTV;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = findViewById(R.id.result_num);
        listView = findViewById(R.id.list_view);
        mistakesTV = findViewById(R.id.mistakes);

        int val = getIntent().getIntExtra("val", 0);

        HashMap<Integer, Boolean> resAns = (HashMap<Integer, Boolean>) getIntent().getSerializableExtra("list");
        ArrayList<String> ansList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            if (resAns.containsKey(i)) {
                if (Objects.requireNonNull(resAns.get(i)).equals(false)) {
                    if (i <= 4) {
                        ansList.add("Question " + i + ": " + VM.textQuestions.get(i).getQ() + "\n"
                                + "Correct answer: " + VM.textQuestions.get(i).getAns());
                    }
                    if (i > 4 && i <= 8) {
                        ansList.add("Question " + i + ": " + VM.testQuestions.get(i - 4).getQ() +
                                "\n" + "Correct answer: " + VM.testQuestions.get(i - 4).getCa());
                    }
                    if (i > 8) {
                        ansList.add("Question " + i + " Image " + (i - 8) + "\n"
                                + "Correct answer: " + VM.imageQuestions.get(i - 8).getAns());
                    }
                }
            } else {
                if (i <= 4) {
                    ansList.add("Question " + i + ": " + VM.textQuestions.get(i).getQ() + "\n"
                            + "Correct answer: " + VM.textQuestions.get(i).getAns());
                }
                if (i > 4 && i <= 8) {
                    ansList.add("Question " + i + ": " + VM.testQuestions.get(i - 4).getQ() +
                            "\n" + "Correct answer: " + VM.testQuestions.get(i - 4).getCa());
                }
                if (i > 8) {
                    ansList.add("Question " + i + " Image " + (i - 8) + "\n"
                            + "Correct answer: " + VM.imageQuestions.get(i - 8).getAns());
                }
            }
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, ansList));
        String res = val + " / 10";
        textView.setText(res);

        if (val == 10){
            mistakesTV.setText(R.string.no_mistakes);
        }

    }
}
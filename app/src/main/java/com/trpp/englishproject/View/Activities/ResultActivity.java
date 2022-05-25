package com.trpp.englishproject.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import com.trpp.englishproject.*;
import com.trpp.englishproject.View.Activities.EntryActivity;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int val = getIntent().getIntExtra("val", 0);

        HashMap<Integer, Boolean> resAns = (HashMap<Integer, Boolean>) getIntent().getSerializableExtra("list");
        ArrayList<String> ansList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            if (resAns.containsKey(i)) {
                if (Objects.requireNonNull(resAns.get(i)).equals(false)) {
                    if (i <= 4) {
                        ansList.add("Question " + i + ": " + EntryActivity.textQuestions.get(i).getQ() + "\n"
                                + "Correct answer: " + EntryActivity.textQuestions.get(i).getAns());
                    }
                    if (i > 4 && i <= 8) {
                        ansList.add("Question " + i + ": " + EntryActivity.testQuestions.get(i - 4).getQ() +
                                "\n" + "Correct answer: " + EntryActivity.testQuestions.get(i - 4).getCa());
                    }
                    if (i > 8) {
                        ansList.add("Question " + i + " Image " + (i - 8) + "\n"
                                + "Correct answer: " + EntryActivity.imageQuestions.get(i - 8).getAns());
                    }
                }
            } else {
                if (i <= 4) {
                    ansList.add("Question " + i + ": " + EntryActivity.textQuestions.get(i).getQ() + "\n"
                            + "Correct answer: " + EntryActivity.textQuestions.get(i).getAns());
                }
                if (i > 4 && i <= 8) {
                    ansList.add("Question " + i + ": " + EntryActivity.testQuestions.get(i - 4).getQ() +
                            "\n" + "Correct answer: " + EntryActivity.testQuestions.get(i - 4).getCa());
                }
                if (i > 8) {
                    ansList.add("Question " + i + " Image " + (i - 8) + "\n"
                            + "Correct answer: " + EntryActivity.imageQuestions.get(i - 8).getAns());
                }
            }
        }
        textView = findViewById(R.id.result_num);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, ansList));
        String res = val + " / 10";
        textView.setText(res);

    }
}
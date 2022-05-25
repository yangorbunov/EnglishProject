package com.trpp.englishproject.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.trpp.englishproject.*;

import com.trpp.englishproject.View.Fragments.ImageQuestionFragment;
import com.trpp.englishproject.View.Fragments.TestQuestionFragment;
import com.trpp.englishproject.View.Fragments.TextQuestionFragment;
import com.trpp.englishproject.ViewModel.VM;

import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, buttonSend;
    int activeTaskId = 1;
    VM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModelProvider(this).get(VM.class);
        btn1 = findViewById(R.id.button_question_1);
        btn2 = findViewById(R.id.button_question_2);
        btn3 = findViewById(R.id.button_question_3);
        btn4 = findViewById(R.id.button_question_4);
        btn5 = findViewById(R.id.button_question_5);
        btn6 = findViewById(R.id.button_question_6);
        btn7 = findViewById(R.id.button_question_7);
        btn8 = findViewById(R.id.button_question_8);
        btn9 = findViewById(R.id.button_question_9);
        btn10 = findViewById(R.id.button_question_10);
        buttonSend = findViewById(R.id.button_send);

        swapQuestions();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, TextQuestionFragment.newInstance(1))
                .commit();

            buttonSend.setOnClickListener(view -> {

                checkAnswers();

                if (activeTaskId < 4) {
                    buttonSend.setText("Next");
                    goToTextQuestion();
                } else if (activeTaskId < 8) {
                    buttonSend.setText("Next");
                    goToTestQuestion();
                } else if (activeTaskId < 10) {
                    buttonSend.setText("Next");
                    if (activeTaskId == 9) {
                        buttonSend.setText("Finish");
                    }
                    goToImageQuestion();
                } else {
                    if (vm.getAnswerList().getValue() == null) {
                        Toast.makeText(this, "Ответьте на хотя бы один вопрос, чтобы посмотреть результат", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("val",checkAnswers());
                        intent.putExtra("list",vm.getAnswerList().getValue());
                        startActivity(intent);
                        finish();
                    }
                }
            });

            btn1.setOnClickListener(view -> {
                activeTaskId = 1;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TextQuestionFragment.newInstance(1))
                        .commit();
            });

            btn2.setOnClickListener(view -> {
                activeTaskId = 2;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TextQuestionFragment.newInstance(2))
                        .commit();
            });

            btn3.setOnClickListener(view -> {
                activeTaskId = 3;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TextQuestionFragment.newInstance(3))
                        .commit();
            });

            btn4.setOnClickListener(view -> {
                activeTaskId = 4;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TextQuestionFragment.newInstance(4))
                        .commit();
            });

            btn5.setOnClickListener(view -> {
                activeTaskId = 5;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TestQuestionFragment.newInstance(5))
                        .commit();
            });

            btn6.setOnClickListener(view -> {
                activeTaskId = 6;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TestQuestionFragment.newInstance(6))
                        .commit();
            });

            btn7.setOnClickListener(view -> {
                activeTaskId = 7;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TestQuestionFragment.newInstance(7))
                        .commit();
            });

            btn8.setOnClickListener(view -> {
                activeTaskId = 8;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, TestQuestionFragment.newInstance(8))
                        .commit();
            });

            btn9.setOnClickListener(view -> {
                activeTaskId = 9;
                buttonSend.setText("Next");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ImageQuestionFragment.newInstance(9))
                        .commit();
            });

            btn10.setOnClickListener(view -> {
                activeTaskId = 10;
                buttonSend.setText("Finish");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ImageQuestionFragment.newInstance(10))
                        .commit();
            });

        }

    private void goToTestQuestion() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, TestQuestionFragment.newInstance(activeTaskId + 1))
                .commit();
        activeTaskId += 1;
    }

    private void goToTextQuestion() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, TextQuestionFragment.newInstance(activeTaskId + 1))
                .commit();
        activeTaskId += 1;
    }

    private void goToImageQuestion() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ImageQuestionFragment.newInstance(activeTaskId + 1))
                .commit();
        activeTaskId += 1;
    }

    private void swapQuestions() {
        for (int i = 0; i < 10; i++){
            int j = new Random().nextInt(EntryActivity.textQuestions.size());
            int k = new Random().nextInt(EntryActivity.textQuestions.size());
            if (j != k) {
                Collections.swap(EntryActivity.textQuestions, j, k);
            }
        }
        for (int i = 0; i < 10; i++){
            int j = new Random().nextInt(EntryActivity.testQuestions.size());
            int k = new Random().nextInt(EntryActivity.testQuestions.size());
            if (j != k) {
                Collections.swap(EntryActivity.testQuestions, j, k);
            }
        }

        for (int i = 0; i < 10; i++){
            int j = new Random().nextInt(EntryActivity.imageQuestions.size());
            int k = new Random().nextInt(EntryActivity.imageQuestions.size());
            if (j != k) {
                Collections.swap(EntryActivity.imageQuestions, j, k);
            }
        }
    }

    private int checkAnswers(){
        int val = 0;
        if(vm.getAnswerList() != null && vm.getAnswerList().getValue() != null) {
            if (vm.getAnswerList().getValue().containsKey(1)) {
                btn1.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(1).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(2)) {
                btn2.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(2).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(3)) {
                btn3.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(3).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(4)) {
                btn4.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(4).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(5)) {
                btn5.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(5).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(6)) {
                btn6.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(6).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(7)) {
                btn7.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(7).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(8)) {
                btn8.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(8).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(9)) {
                btn9.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(9).equals(true)) {
                    val += 1;
                }
            }
            if (vm.getAnswerList().getValue().containsKey(10)) {
                btn10.setBackgroundColor(getResources().getColor(R.color.blue));
                if (vm.getAnswerList().getValue().get(10).equals(true)) {
                    val += 1;
                }
            }
        }
        return val;
    }
}
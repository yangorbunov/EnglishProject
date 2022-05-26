package com.trpp.englishproject.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.trpp.englishproject.*;

import com.trpp.englishproject.Model.TestQuestion;
import com.trpp.englishproject.ViewModel.VM;


public class TestQuestionFragment extends Fragment {

    private final int questionNo;
    VM vm;
    TextView questionTV, noQuestion;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;

    public TestQuestionFragment(int questionNo) {
        this.questionNo = questionNo;
    }

    public static TestQuestionFragment newInstance(int questionNo) {
        return new TestQuestionFragment(questionNo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_question, container, false);
        questionTV = view.findViewById(R.id.QuestionTextView);
        radioGroup = view.findViewById(R.id.rg);
        rb1 = view.findViewById(R.id.radio_1);
        rb2 = view.findViewById(R.id.radio_2);
        rb3 = view.findViewById(R.id.radio_3);
        rb4 = view.findViewById(R.id.radio_4);
        noQuestion = view.findViewById(R.id.number_q);
        vm = new ViewModelProvider(requireActivity()).get(VM.class);
        TestQuestion testQuestion = VM.testQuestions.get(questionNo - 4);
        questionTV.setText(testQuestion.getQ());
        noQuestion.setText(String.valueOf(questionNo));
        rb1.setText(testQuestion.getAns().get("a1"));
        rb2.setText(testQuestion.getAns().get("a2"));
        rb3.setText(testQuestion.getAns().get("a3"));
        rb4.setText(testQuestion.getAns().get("a4"));
        String correctAns = testQuestion.getCa();


        radioGroup.setOnCheckedChangeListener((radioGroup, i) ->
                vm.setAnswer(questionNo, rb1.isChecked() && rb1.getText().equals(correctAns) ||
                rb2.isChecked() && rb2.getText().equals(correctAns) ||
                rb3.isChecked() && rb3.getText().equals(correctAns) ||
                rb4.isChecked() && rb4.getText().equals(correctAns)));

        return view;
    }
}
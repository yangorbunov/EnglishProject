package com.trpp.englishproject.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.trpp.englishproject.*;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.trpp.englishproject.Model.TextQuestion;
import com.trpp.englishproject.ViewModel.VM;



public class TextQuestionFragment extends Fragment {

    private final int questionNo;
    TextView noView, questionView;
    EditText answer;
    VM vm;


    public TextQuestionFragment(int questionNo) {
        this.questionNo = questionNo;
    }

    public static TextQuestionFragment newInstance(int questionNo) {
        return new TextQuestionFragment(questionNo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_question, container, false);
        noView = view.findViewById(R.id.number_q);
        questionView = view.findViewById(R.id.questionTextView);
        vm = new ViewModelProvider(requireActivity()).get(VM.class);
        answer = view.findViewById(R.id.text_ans);
        TextQuestion textQuestion = VM.textQuestions.get(questionNo);
        noView.setText(String.valueOf(questionNo));
        questionView.setText(textQuestion.getQ());

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                vm.setAnswer(questionNo, editable.toString().equalsIgnoreCase(textQuestion.getAns()));
            }
        });

        return view;
    }


}
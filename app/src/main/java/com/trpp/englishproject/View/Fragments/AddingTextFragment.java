package com.trpp.englishproject.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trpp.englishproject.R;
import com.trpp.englishproject.ViewModel.VM;

public class AddingTextFragment extends Fragment {

    EditText question, answer;
    Button shareBtn;


    public AddingTextFragment() {
    }


    public static AddingTextFragment newInstance() {
        return new AddingTextFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_adding, container, false);
        question = view.findViewById(R.id.adding_text_question);
        answer = view.findViewById(R.id.adding_text_ans);
        shareBtn = view.findViewById(R.id.button_share_text);

        shareBtn.setOnClickListener(v -> {
            if (answer.getText() != null && question.getText() != null) {
                if (!answer.getText().toString().equals("") && !question.getText().toString().equals("")) {
                    Toast.makeText(requireActivity(), "Вопрос добавлен", Toast.LENGTH_SHORT).show();
                    VM.writeTextQuestionOnDB(question.getText().toString(), answer.getText().toString());
                } else {
                    Toast.makeText(requireActivity(), "Введите вопрос и ответ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  view;
    }
}
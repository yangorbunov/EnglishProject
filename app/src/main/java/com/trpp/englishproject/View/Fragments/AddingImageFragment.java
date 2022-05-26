package com.trpp.englishproject.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trpp.englishproject.*;
import com.trpp.englishproject.ViewModel.VM;


public class AddingImageFragment extends Fragment {

    EditText question, answer;
    Button shareBtn;

    public AddingImageFragment() {
    }

    public static AddingImageFragment newInstance() {
        return new AddingImageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_adding, container, false);
        question = view.findViewById(R.id.adding_image_question);
        answer = view.findViewById(R.id.adding_image_ans);
        shareBtn = view.findViewById(R.id.button_share_image);

        shareBtn.setOnClickListener(v -> {
            if (answer.getText() != null && question.getText() != null) {
                if (!answer.getText().toString().equals("") && !question.getText().toString().equals("")) {
                    Toast.makeText(requireActivity(), "Вопрос добавлен", Toast.LENGTH_SHORT).show();
                VM.writeImageQuestionOnDB((question.getText().toString()), answer.getText().toString());
            }
            else {
                    Toast.makeText(requireActivity(), "Введите ссылку и ответ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  view;
    }
}
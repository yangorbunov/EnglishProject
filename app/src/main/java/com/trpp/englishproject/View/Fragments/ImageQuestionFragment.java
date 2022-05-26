package com.trpp.englishproject.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trpp.englishproject.Model.ImageQuestion;
import com.trpp.englishproject.ViewModel.VM;
import com.trpp.englishproject.*;


public class ImageQuestionFragment extends Fragment {

    private final int questionNo;
    VM vm;
    TextView noView;
    EditText answer;
    ImageView imageView;

    public ImageQuestionFragment(int questionNo) {
        this.questionNo = questionNo;
    }

    public static ImageQuestionFragment newInstance(int questionNo) {
        return new ImageQuestionFragment(questionNo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_question, container, false);

        noView = view.findViewById(R.id.number_q_img);
        imageView = view.findViewById(R.id.img_pic);
        vm = new ViewModelProvider(requireActivity()).get(VM.class);
        answer = view.findViewById(R.id.text_ans_img);
        ImageQuestion imageQuestion = VM.imageQuestions.get(questionNo - 8);
        noView.setText(String.valueOf(questionNo));
        Glide.with(requireActivity()).load(imageQuestion.getQ()).into(imageView);

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                vm.setAnswer(questionNo, editable.toString().equalsIgnoreCase(imageQuestion.getAns()));
            }
        });

        return view;
    }
}
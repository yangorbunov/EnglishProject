package com.trpp.englishproject.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.trpp.englishproject.R;
import com.trpp.englishproject.ViewModel.VM;

import java.util.ArrayList;
import java.util.HashMap;

public class AddingTestFragment extends Fragment {

    EditText question;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    EditText et1, et2, et3, et4;
    Button shareBtn;


    public AddingTestFragment() { }


    public static AddingTestFragment newInstance() {
        return new AddingTestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_adding, container, false);
        question = view.findViewById(R.id.adding_test_question);
        radioGroup = view.findViewById(R.id.rg_adding_test);
        et1 = view.findViewById(R.id.adding_test_1);
        et2 = view.findViewById(R.id.adding_test_2);
        et3 = view.findViewById(R.id.adding_test_3);
        et4 = view.findViewById(R.id.adding_test_4);
        rb1 = view.findViewById(R.id.radio_1_adding);
        rb2 = view.findViewById(R.id.radio_2_adding);
        rb3 = view.findViewById(R.id.radio_3_adding);
        rb4 = view.findViewById(R.id.radio_4_adding);
        shareBtn = view.findViewById(R.id.button_share_test);

        shareBtn.setOnClickListener(v -> {

            if (question.getText() != null &&
            et1.getText() != null && et2.getText() != null &&
            et3.getText() != null && et4.getText() != null) {
                if (!question.getText().toString().equals("") &&
                        !et1.getText().toString().equals("") && !et2.getText().toString().equals("") &&
                        !et3.getText().toString().equals("") && !et4.getText().toString().equals("")) {

                    ArrayList<String> list = new ArrayList<>();
                    list.add(et1.getText().toString());
                    list.add(et2.getText().toString());
                    list.add(et3.getText().toString());
                    list.add(et4.getText().toString());
                    if (VM.checkTestDuplicates(list)) {
                        if (rb1.isChecked()) {
                            pushTask(et1.getText().toString());
                        } else if (rb2.isChecked()) {
                            pushTask(et2.getText().toString());
                        } else if (rb3.isChecked()) {
                            pushTask(et3.getText().toString());
                        } else if (rb4.isChecked()) {
                            pushTask(et4.getText().toString());
                        } else {
                            Toast.makeText(requireActivity(), "Выберите правильный вариант ответа", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireActivity(), "Ответы не должны совпадать", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireActivity(), "Введите вопрос и варианты ответа", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }

    public void pushTask(String correctAnswer){
        HashMap<String,String> map = new HashMap<>();
        map.put("a1",et1.getText().toString());
        map.put("a2",et2.getText().toString());
        map.put("a3",et3.getText().toString());
        map.put("a4",et4.getText().toString());
        VM.writeTestQuestionOnDB(question.getText().toString(),map,correctAnswer);
        Toast.makeText(requireActivity(), "Вопрос добавлен", Toast.LENGTH_SHORT).show();
    }
}
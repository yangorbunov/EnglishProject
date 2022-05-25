package com.trpp.englishproject.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trpp.englishproject.Model.ImageQuestion;
import com.trpp.englishproject.Model.TestQuestion;
import com.trpp.englishproject.Model.TextQuestion;

import java.util.HashMap;
import java.util.Objects;

public class VM extends ViewModel {

    MutableLiveData<HashMap<Integer,Boolean>> answerData;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public MutableLiveData<HashMap<Integer,Boolean>> getAnswerList() {
        if (answerData == null){
            answerData = new MutableLiveData<>();
        }
        return answerData;
    }

    public void setAnswer(int answer, Boolean cor) {
        if (answerData == null){
            answerData = new MutableLiveData<>();
        }
        if (answerData.getValue() == null){
            HashMap<Integer,Boolean> map = new HashMap<>();
            map.put(answer,cor);
            answerData.setValue(map);
        }
        else {
            HashMap<Integer,Boolean> list = new HashMap<>(
                    Objects.requireNonNull(getAnswerList().getValue()));
            list.put(answer,cor);
            answerData.setValue(list);
        }
    }

    public void writeTextQuestionOnDB (String question, String answer){
        TextQuestion textQuestion = new TextQuestion();
        textQuestion.setQ(question);
        textQuestion.setAns(answer);
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Text");
        DatabaseReference newTextQRef = itemsRef.push();
        newTextQRef.setValue(textQuestion);
    }

    public void writeTestQuestionOnDB (String question, HashMap<String,String> answers, String correctAnswer){
        TestQuestion testQuestion = new TestQuestion();
        testQuestion.setQ(question);
        testQuestion.setAns(answers);
        testQuestion.setCa(correctAnswer);
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Test");
        DatabaseReference newTextQRef = itemsRef.push();
        newTextQRef.setValue(testQuestion);
    }

    public void writeImageQuestionOnDB (String imageUri, String answer){
        ImageQuestion imageQuestion = new ImageQuestion();
        imageQuestion.setQ(imageUri);
        imageQuestion.setAns(answer);
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Picture");
        DatabaseReference newTextQRef = itemsRef.push();
        newTextQRef.setValue(imageQuestion);
    }
}

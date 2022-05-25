package com.trpp.englishproject.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Objects;

public class VM extends ViewModel {

    MutableLiveData<HashMap<Integer,Boolean>> answerData;

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
}

package com.trpp.englishproject.ViewModel;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trpp.englishproject.Model.ImageQuestion;
import com.trpp.englishproject.Model.TestQuestion;
import com.trpp.englishproject.Model.TextQuestion;
import com.trpp.englishproject.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yan Gorbunov
 * @version 1.0
 * ViewModel class, which contains static lists ans live data hash map with test results.
 */
public class VM extends ViewModel {

    MutableLiveData<HashMap<Integer,Boolean>> answerData;
    public static ArrayList<TextQuestion> textQuestions =  new ArrayList<>();
    public static ArrayList<TestQuestion> testQuestions = new ArrayList<>();
    public static ArrayList<ImageQuestion> imageQuestions = new ArrayList<>();
    public static ArrayList<User> usersList = new ArrayList<>();
    static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    /**
     *
     * Getter method for live data
     * @return answerData - value of live data
     */
    public MutableLiveData<HashMap<Integer,Boolean>> getAnswerList() {
        if (answerData == null){
            answerData = new MutableLiveData<>();
        }
        return answerData;
    }

    /**
     * Writes added text question to database
     * @param answer - users answer for question
     * @param cor - boolean value if answer is correct
     */
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

    /**
     * Writes added text question to database
     * @param question - added question
     * @param answer - added answer
     */
    public static void writeTextQuestionOnDB (String question, String answer){
        TextQuestion textQuestion = new TextQuestion();
        textQuestion.setQ(question);
        textQuestion.setAns(answer);
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Text");
        DatabaseReference newTextQRef = itemsRef.push();
        newTextQRef.setValue(textQuestion);
    }

    /**
     * Writes added test question to database
     * @param question - added question
     * @param answers - added map of answers
     * @param correctAnswer - added correct answer
     */
    public static void writeTestQuestionOnDB (String question, HashMap<String,String> answers, String correctAnswer){
        TestQuestion testQuestion = new TestQuestion();
        testQuestion.setQ(question);
        testQuestion.setAns(answers);
        testQuestion.setCa(correctAnswer);
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Test");
        DatabaseReference newTextQRef = itemsRef.push();
        newTextQRef.setValue(testQuestion);
    }

    /**
     * Writes added image question to database
     * @param imageUri - added image URL
     * @param answer - added correct answer
     */
    public static void writeImageQuestionOnDB (String imageUri, String answer){
        ImageQuestion imageQuestion = new ImageQuestion();
        imageQuestion.setQ(imageUri);
        imageQuestion.setAns(answer);
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Picture");
        DatabaseReference newTextQRef = itemsRef.push();
        newTextQRef.setValue(imageQuestion);
    }

    /**
     * Finds and returns user by userName if exists
     * @param userName - value if userName field
     * @return user - User class object
     */
    public static User getUserByUserName(String userName){
        User user = new User();
        for (User u : usersList){
            if(u.getUserName().equals(userName)){
                user = u;
            }
        }
        return user;
    }

    /**
     * Finds and returns true if user exists
     * @param user - User class object
     * @return ifCorrect - Boolean method result
     */
    public static boolean checkUser (String user){
        boolean ifCorrect = false;
        for (User u : usersList){
            if (u.getUserName().equals(user)) {
                ifCorrect = true;
                break;
            }
        }
        return ifCorrect;
    }

    /**
     * Finds and returns true is answers do not duplicate
     * @param list - list of answers
     * @return ifCorrect - Boolean method result
     */
    public static boolean checkTestDuplicates(ArrayList<String> list){
        HashSet<String> set = new HashSet<>(list);
        return list.size() == set.size();
    }

    /**
     * Reads users from database
     */
    public static void readUsersDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Users");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!usersList.isEmpty()){
                    usersList.clear();
                }

                for(DataSnapshot ds : snapshot.getChildren()) {
                    User u = ds.getValue(User.class);
                    if (u != null) {
                        usersList.add(u);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /**
     * Reads text questions from database
     */
    public static void readTextDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Text");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!textQuestions.isEmpty()){
                    testQuestions.clear();
                }

                for(DataSnapshot ds : snapshot.getChildren()) {
                    TextQuestion o = ds.getValue(TextQuestion.class);
                    if (o != null) {
                        textQuestions.add(o);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /**
     * Reads test questions from database
     */
    public static void readTestDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Test");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!testQuestions.isEmpty()){
                    testQuestions.clear();
                }

                for(DataSnapshot ds : snapshot.getChildren()) {
                    TestQuestion o = ds.getValue(TestQuestion.class);
                    if (o != null) {
                        testQuestions.add(o);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /**
     * Reads image questions from database
     */
    public static void readImageDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Picture");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!imageQuestions.isEmpty()){
                    imageQuestions.clear();
                }

                for(DataSnapshot ds : snapshot.getChildren()) {
                    ImageQuestion o = ds.getValue(ImageQuestion.class);
                    if (o != null) {
                        imageQuestions.add(o);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

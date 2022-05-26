package com.trpp.englishproject.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import com.trpp.englishproject.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trpp.englishproject.Model.ImageQuestion;
import com.trpp.englishproject.Model.TestQuestion;
import com.trpp.englishproject.Model.TextQuestion;
import com.trpp.englishproject.Model.User;
import com.trpp.englishproject.ViewModel.VM;

import java.util.ArrayList;

public class EntryActivity extends AppCompatActivity {

    Button buttonHelp, buttonStart, buttonAdd;
    EditText userNameET, passWordET;
    public static ArrayList<TextQuestion> textQuestions;
    public static ArrayList<TestQuestion> testQuestions;
    public static ArrayList<ImageQuestion> imageQuestions;
    public static ArrayList<User> usersList;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        textQuestions = new ArrayList<>();
        testQuestions = new ArrayList<>();
        imageQuestions = new ArrayList<>();
        usersList = new ArrayList<>();

        buttonHelp = findViewById(R.id.buttonHelp);
        buttonStart = findViewById(R.id.buttonStart);
        buttonAdd = findViewById(R.id.buttonAdd);
        userNameET = findViewById(R.id.user_name);
        passWordET = findViewById(R.id.password);

        readTextTasksFromDB();
        readTestTasksFromDB();
        readImageTasksFromDB();
        readUsersFromDB();


        buttonHelp.setOnClickListener(view -> {
            Intent intent = new Intent(EntryActivity.this, HelpActivity.class);
            startActivity(intent);
        });

        buttonStart.setOnClickListener(view -> {
            Intent intent = new Intent(EntryActivity.this, LoadingActivity.class);
            startActivity(intent);
        });

            buttonAdd.setOnClickListener(view -> {
                if (userNameET.getText() != null && passWordET.getText() != null) {
                    if (!userNameET.getText().toString().equals("") && !passWordET.getText().toString().equals("")) {
                        if (VM.checkUser(userNameET.getText().toString())) {
                            User user = VM.getUserByUserName(userNameET.getText().toString());
                            if (user.getPassword().equals(passWordET.getText().toString())) {
                                Intent intent = new Intent(EntryActivity.this, AddingActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this,
                                        "Неверный пароль",
                                        Toast.LENGTH_SHORT).show(); }

                        } else {
                            Toast.makeText(this,
                                    "Пользователь не найден",
                                    Toast.LENGTH_SHORT).show(); }
                        } else {
                            Toast.makeText(this,
                                    "Для добавления вопросов введите логин и пароль",
                                    Toast.LENGTH_SHORT).show(); }
                }
            });
        }

    private void readUsersFromDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Users");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {
                    User u = ds.getValue(User.class);
                    if (u != null) {
                        usersList.add(u);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"DB error user",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readTextTasksFromDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Text");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {
                    TextQuestion o = ds.getValue(TextQuestion.class);
                    if (o != null) {
                        textQuestions.add(o);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"DB error text",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readTestTasksFromDB(){
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
                Toast.makeText(getApplicationContext(),"DB error test",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readImageTasksFromDB(){

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
                Toast.makeText(getApplicationContext(),"DB error image",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

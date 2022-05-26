package com.trpp.englishproject.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.trpp.englishproject.*;

import com.trpp.englishproject.Model.User;
import com.trpp.englishproject.ViewModel.VM;

/**
 * @author Yan Gorbunov
 * @version 1.0
 * View class of questions, which contains buttons and authorization checking.
 */
public class EntryActivity extends AppCompatActivity {

    Button buttonHelp, buttonStart, buttonAdd;
    EditText userNameET, passWordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        buttonHelp = findViewById(R.id.buttonHelp);
        buttonStart = findViewById(R.id.buttonStart);
        buttonAdd = findViewById(R.id.buttonAdd);
        userNameET = findViewById(R.id.user_name);
        passWordET = findViewById(R.id.password);

        VM.readTextDB();
        VM.readTestDB();
        VM.readImageDB();
        VM.readUsersDB();


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
}

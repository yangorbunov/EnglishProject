package com.trpp.englishproject.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trpp.englishproject.R;


public class TextAddingFragment extends Fragment {

    public TextAddingFragment() {
    }

    public static TextAddingFragment newInstance() {
        return new TextAddingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text_adding, container, false);
    }
}
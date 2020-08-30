package com.example.shareidea;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.shareidea.databinding.FragmentMemoBinding;

public class MemoFragment {

    public static MemoFragment newInstanct(){
        return new MemoFragment();
    }

    private Context mContext;
    private FragmentMemoBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo, container, false);


        return binding.getRoot();
    }

}
package com.example.shareidea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.shareidea.databinding.FragmentShareBinding;

public class ShareFragment extends Fragment {

    public static ShareFragment newInstance(){
        return new ShareFragment();
    }

    private Context mContext;
    private FragmentShareBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_share, container, false);

        binding.fabShare.setOnClickListener(view -> {
            startActivity(new Intent(mContext, NewShare.class));
        });

        return binding.getRoot();
    }

}
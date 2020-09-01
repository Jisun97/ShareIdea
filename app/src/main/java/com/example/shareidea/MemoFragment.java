package com.example.shareidea;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shareidea.databinding.FragmentMemoBinding;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MemoFragment extends Fragment {

    public static MemoFragment newInstance() {
        return new MemoFragment();
    }

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private ObservableArrayList<MemoModel> items = new ObservableArrayList<>();


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
        binding.setItems(items);

        binding.fabMemo.setOnClickListener(view -> startActivity(new Intent(mContext, NewMemo.class)));

        binding.recyclerMemo.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        MemoAdapter adapter = new MemoAdapter();
        binding.recyclerMemo.setAdapter(adapter);

        adapter.setOnItemClickListener((view, item) -> {
        });

        adapter.setOnItemLongClickListener((view, item) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("메모 삭제").setMessage("이 메모를 삭제할까요?");
            builder.setPositiveButton("삭제", (dialogInterface, i) -> {
                firebaseFirestore
                        .collection("memo")
                        .document(UserCache.getUser(mContext).getEmail())
                        .update("memo", FieldValue.arrayRemove(MemoModel.toMap(item)))
                        .addOnSuccessListener(runnable ->
                                Toast.makeText(mContext, "성공적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show());
            });
            builder.setNegativeButton("취소", (dialogInterface, i) -> {
            }).show();

            return true;
        });

        firebaseFirestore
                .collection("memo")
                .document(UserCache.getUser(mContext).getEmail())
                .addSnapshotListener((value, error) -> {
                    if (error != null || value == null) return;
                    items.clear();
                    List<HashMap<String, String>> list = (List<HashMap<String, String>>) value.get("memo");
                    if (list == null) return;
                    for (HashMap<String, String> m : list) {
                        items.add(MemoModel.fromMap(m));
                    }
                    Collections.reverse(items);
                });

        return binding.getRoot();
    }

}
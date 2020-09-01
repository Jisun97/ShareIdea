package com.example.shareidea;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.shareidea.databinding.ActivityNewShareBinding;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class NewShare extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private ActivityNewShareBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_share);
        binding.setActivity(this);
        binding.setTitle("");
        binding.setSub("");
        binding.setText("");

        getWindow().setStatusBarColor(Color.parseColor("#fbfbfb"));

    }

    public void uploadMemo() {
        String title = binding.getTitle(), sub = binding.getSub(), text = binding.getText();
        if (title.isEmpty() || sub.isEmpty() || text.isEmpty()) return;

        MemoModel model = new MemoModel();
        model.setTitle(title);
        model.setSub(sub);
        model.setText(text);
        model.setUser(UserCache.getUser(this).getEmail());
        model.setTime(getTime());

        firebaseFirestore
                .collection("memo")
                .document(UserCache.getUser(this).getEmail())
                .update("memo", FieldValue.arrayUnion(MemoModel.toMap(model)))
                .addOnSuccessListener(runnable -> {
                    Toast.makeText(this, "성공적으로 업로드 되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(runnable -> {
                    HashMap<String, List<Object>> map = new HashMap<>();
                    map.put("memo", null);

                    firebaseFirestore
                            .collection("memo")
                            .document(UserCache.getUser(this).getEmail())
                            .set(map)
                            .addOnSuccessListener(runnable1 -> uploadMemo());
                });
    }

    private String getTime() {
        return new SimpleDateFormat("yyyy/MM/dd hh:mm aa", Locale.ENGLISH).format(new Date());
    }
}
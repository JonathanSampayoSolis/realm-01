package com.example.jjsampayo.realm_01.presentation.add;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.widget.Toast;

import com.example.jjsampayo.realm_01.R;
import com.example.jjsampayo.realm_01.data.entities.Task;
import com.example.jjsampayo.realm_01.data.repos.TaskRepo;

import java.util.Objects;

public class AddDialogFragment extends DialogFragment {

    private TaskRepo repo;

    public static AddDialogFragment newInstance() {
        Bundle args = new Bundle();

        AddDialogFragment fragment = new AddDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setCancelable(false);
        Objects.requireNonNull(getDialog().getWindow()).setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLifecycle().addObserver(repo = new TaskRepo());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatEditText editText   = view.findViewById(R.id.dialog_edt_add);
        AppCompatButton buttonAdd    = view.findViewById(R.id.dialog_btn_add);
        AppCompatButton buttonCancel = view.findViewById(R.id.dialog_btn_cancel);

        buttonCancel.setOnClickListener(v -> getDialog().dismiss());

        buttonAdd.setOnClickListener((v) -> {
            if (editText.getText().toString().length() > 0) {
                repo.insertTask(new Task(editText.getText().toString()));
                getDialog().dismiss();
                Toast.makeText(getActivity(), R.string.add_success, Toast.LENGTH_SHORT).show();
            } else {
                editText.setError(getContext().getString(R.string.input_empty));
            }
        });

    }
}

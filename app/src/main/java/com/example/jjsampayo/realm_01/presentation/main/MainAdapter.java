package com.example.jjsampayo.realm_01.presentation.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.jjsampayo.realm_01.R;
import com.example.jjsampayo.realm_01.data.entities.Task;
import com.example.jjsampayo.realm_01.data.repos.TaskRepo;
import com.example.jjsampayo.realm_01.databinding.ItemFraMainBinding;

import java.util.List;
import java.util.Objects;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Task> data;
    private TaskRepo repo;

    MainAdapter(List<Task> data, TaskRepo repo) {
        this.data  = data;
        this.repo = repo;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFraMainBinding binding = ItemFraMainBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false);

        return new MainViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.binding.setTask(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        ItemFraMainBinding binding;

        MainViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

            ((CheckBox) itemView.findViewById(R.id.item_main_chk))
                    .setOnCheckedChangeListener((buttonView, isChecked) ->
                            repo.setTaskState(data.get(getAdapterPosition()).getId(), isChecked));

            itemView.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext())
                        .setTitle(R.string.title_dialog_delete)
                        .setMessage(R.string.delete_dialog)
                        .setPositiveButton(R.string.yes, (dialog, which) -> {
                            repo.deleteTask(data.get(getAdapterPosition()).getId());
                            notifyDataSetChanged();
                                })
                        .setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

                AlertDialog alertDialog = builder.create();

                alertDialog.setCancelable(false);
                alertDialog.show();

                return true;
            });
        }
    }
}

package com.example.jjsampayo.realm_01.data.repos;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.example.jjsampayo.realm_01.data.entities.Task;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class TaskRepo implements LifecycleObserver {

    private final String TAG = TaskRepo.class.getSimpleName().toUpperCase();
    private Realm realm;

    public TaskRepo() {
        this.realm = Realm.getDefaultInstance();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        realm = Realm.getDefaultInstance();
    }

    public List<Task> getAllTask() {
        Log.d(TAG, "Task Count : " + String.valueOf(realm.where(Task.class).count()));
        RealmResults<Task> tasks = realm.where(Task.class).findAll();

        return tasks;
    }

    public void insertTask(Task task) {
        realm.executeTransactionAsync(realm -> realm.createObject(Task.class,
                task.getId()).setName(task.getName()));
    }

    public void deleteTask(String id) {
        realm.beginTransaction();
        Objects.requireNonNull(realm.where(Task.class)
                .equalTo("id", id).findFirst()).deleteFromRealm();
        realm.commitTransaction();
    }

    public void updateTask(String id, Task newTask) {
        realm.executeTransactionAsync(realm1 -> {
            Task oldTask = Objects.requireNonNull(realm.where(Task.class)
                    .equalTo("id", id).findFirst());

            oldTask.setName(newTask.getName());
            oldTask.setState(newTask.isState());
        });
    }

    public void setTaskState(String id, boolean state) {
        realm.beginTransaction();
        Task oldTask = Objects.requireNonNull(realm.where(Task.class)
                .equalTo("id", id).findFirst());
        oldTask.setState(state);
        realm.commitTransaction();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        realm.close();
    }


}

package com.example.jjsampayo.realm_01.data.entities;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Task extends RealmObject {

    @Required
    @Index
    @PrimaryKey
    private String id;

    private String name;

    private boolean state;

    public Task() {
        this.id = UUID.randomUUID().toString();
        this.state = false;
    }

    public Task(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.state = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


}

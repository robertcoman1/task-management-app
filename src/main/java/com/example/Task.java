package com.example;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String taskName;
    private Boolean done;

    public Task(){

    }

    public Task(Integer id, String taskName, Boolean done) {
        this.id = id;
        this.taskName = taskName;
        this.done = done;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public Boolean getDone() {
        return done;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Task t = (Task) o;
        return this.id == t.id
                && this.taskName.equals(t.taskName)
                && this.done == t.done;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, done);
    }
}

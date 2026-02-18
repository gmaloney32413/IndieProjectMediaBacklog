package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.List;

public class User {
    int userId;
    String email;
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BacklogEntry> backlogEntries;


    public User(){}


    public User(int id, String email, String password) {
        this.userId = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *  gets user's password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public List<BacklogEntry> getBacklogEntries() {
        return backlogEntries;
    }

    public void setBacklogEntries(List<BacklogEntry> backlogEntries) {
        this.backlogEntries = backlogEntries;
    }

}

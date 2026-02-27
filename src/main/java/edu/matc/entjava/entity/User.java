package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long userId;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BacklogEntry> backlogEntries;


    public User(){}


    public User(Long id, String email, String password) {
        this.userId = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
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

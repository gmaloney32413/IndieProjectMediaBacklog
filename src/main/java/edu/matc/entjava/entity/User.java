package edu.matc.entjava.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "cognito_sub", nullable = false, unique = true)
    private String cognitoSub;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BacklogEntry> backlogEntries;

    /**
     * Instantiates a new User.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param id         the id
     * @param cognitoSub the cognito sub
     * @param email      the email
     * @param username   the username
     * @param name       the name
     */
    public User(Long id, String cognitoSub, String email, String username, String name) {
        this.userId = id;
        this.cognitoSub = cognitoSub;
        this.email = email;
        this.username = username;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return userId;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.userId = id;
    }

    /**
     * Gets cognito sub.
     *
     * @return the cognito sub
     */
    public String getCognitoSub() {
        return cognitoSub;
    }

    /**
     * Sets cognito sub.
     *
     * @param cognitoSub the cognito sub
     */
    public void setCognitoSub(String cognitoSub) {
        this.cognitoSub = cognitoSub;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets backlog entries.
     *
     * @return the backlog entries
     */
    public List<BacklogEntry> getBacklogEntries() {
        return backlogEntries;
    }

    /**
     * Sets backlog entries.
     *
     * @param backlogEntries the backlog entries
     */
    public void setBacklogEntries(List<BacklogEntry> backlogEntries) {
        this.backlogEntries = backlogEntries;
    }
}
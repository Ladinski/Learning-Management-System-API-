package org.example.lms.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private boolean isInstructor;


    public User() {}

    public User(String name, String email, boolean isInstructor) {
        this.name = name;
        this.email = email;
        this.isInstructor = isInstructor;
    }


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean isInstructor) {
        this.isInstructor = isInstructor;
    }
}

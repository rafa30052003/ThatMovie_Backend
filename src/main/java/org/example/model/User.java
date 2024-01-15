package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 256, nullable = false)
    private String name;
    @Column(name = "surname", length = 256)
    private String surname;
    @Column(name = "username", length = 256, nullable = false)
    private String username;
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    @Column(name = "avatar", length = 256)
    private String avatar;
    @Column(name = "bio", length = 256)
    private String bio;


    @OneToMany(mappedBy = "user")
    private List<Review> reviews;


    @OneToMany(mappedBy = "user")
    private List<PlayList> playlists;

    public User() {
        this(-1, "name", "surname", "username", "password", "avatar", "bio");
    }

    public User(int id, String name, String surname, String username, String password, String avatar, String bio) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}

package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "list")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 256, nullable = false)
    private String name;
    @Column(name = "like")
    private Boolean like;
    @Column(name = "created_at")
    private LocalDate created_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user_id;


    public PlayList() {
    this(-1, "name", false, LocalDate.now(), null);
    }

    public PlayList(int id, String name, Boolean like, LocalDate created_at, User user_id) {
        this.id = id;
        this.name = name;
        this.like = like;
        this.created_at = created_at;
        this.user_id = user_id;
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

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", like='" + like + '\'' +
                ", created_at=" + created_at +
                ", user_id=" + user_id +
                '}';
    }
}

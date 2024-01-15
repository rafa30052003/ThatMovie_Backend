package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "rating")
    private double rating;
    @Column(name = "created_at")
    private LocalDate created_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "movie_id")
    private int movie;

    public Review() {
        this(0, "", 0.0, LocalDate.now(), new User(), 0);
    }

    public Review(int id, String content, double rating, LocalDate date, User user, int movie) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.created_at = date;
        this.user = user;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate date) {
        this.created_at = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", date=" + created_at +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}

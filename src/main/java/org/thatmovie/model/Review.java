package org.thatmovie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Member member;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Review() {
        this(0, "", 0.0, LocalDate.now(), new Member(), new Movie());
    }

    public Review(int id, String content, double rating, LocalDate created_at, Member member, Movie movie) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.created_at = created_at;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member user) {
        this.member = user;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", date=" + created_at +
                ", user=" + member.getId() +
                ", movie=" + movie.getId() +
                '}';
    }
}

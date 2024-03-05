package org.thatmovie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "playlist")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", length = 256, nullable = false)
    private String name;
    @Column(name = "liked")
    private Boolean like;
    @Column(name = "created_at")
    private LocalDate created_at;



    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Member member;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "list_movie",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))

    private List<Movie> movies;


    public PlayList() {
    this(-1, "name", false, LocalDate.now(), null);
    }

    public PlayList(String name, Boolean like, LocalDate created_at, Member member) {
        this.name = name;
        this.like = like;
        this.created_at = created_at;
        this.member = member;
    }

    public PlayList(int id, String name, Boolean like, LocalDate created_at, Member member) {
        this.id = id;
        this.name = name;
        this.like = like;
        this.created_at = created_at;
        this.member = member;
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

    public Member getUser() {
        return member;
    }

    public void setUser(Member user) {
        this.member = user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        if(this.movies == null) {
            this.movies = new java.util.ArrayList<>();
        }
        if(this.movies.contains(movie)) {
            return;
        }
        this.movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        if(this.movies == null) {
            return;
        }
        if(!this.movies.contains(movie)) {
            return;
        }
        this.movies.remove(movie);
    }


    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", like='" + like + '\'' +
                ", created_at=" + created_at +
                ", user_id=" + member.getId() +
                '}';
    }
}

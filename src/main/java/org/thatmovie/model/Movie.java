package org.thatmovie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.thatmovie.model.PlayList;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Column(name = "adult")
    private boolean adult;
    @Column(name = "backdrop_path")
    private String backdrop_path;
    @Column(name = "genre_ids")
    private Integer[] genre_ids;
    @Column(name = "genre_names")
    private String[] genre_names;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "original_language")
    private String original_language;
    @Column(name = "original_title")
    private String original_title;
    @Column(name = "overview")
    private String overview;
    @Column(name = "popularity")
    private Double popularity;
    @Column(name = "poster_path")
    private String poster_path;
    @Column(name = "release_date")
    private String release_date;
    @Column(name = "title")
    private String title;
    @Column(name = "video")
    private boolean video;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "list_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "list_id"))
    @JsonIgnore
    private List<PlayList> playList;


   // private CreditsDTO credits;


    public Movie(boolean adult, String backdrop_path, Integer[] genre_ids, String[] genre_names, Integer id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, String title, boolean video) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.genre_names = genre_names;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;

        ///this.credits = credits;
    }

    public Movie() {
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Integer[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(Integer[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String[] getGenre_names() {
        return genre_names;
    }

    public void setGenre_names(String[] genre_names) {
        this.genre_names = genre_names;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void addToPlayList(PlayList playList) {
        if (this.playList == null) {
            this.playList = new ArrayList<>();

        }
        if(this.playList.contains(playList)) {
            return;
        }
        this.playList.add(playList);
    }

/*
    public CreditsDTO getCredits() {
        return credits;
    }

    public void setCredits(CreditsDTO credits) {
        this.credits = credits;
    }
*/
    public List<PlayList> getPlayList() {
        return playList;
    }



    public void setPlayList(List<PlayList> playList) {
        this.playList = playList;
    }
}

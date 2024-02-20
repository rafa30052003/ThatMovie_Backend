package org.thatmovie.model.DTO;

public class MovieDTO {
    private boolean adult;
    private String backdrop_path;
   private GenreDTO[] genres;

    private Integer id;

    private String original_language;

    private String original_title;

    private String overview;

    private Double popularity;

    private String poster_path;

    private String release_date;

    private String title;

    private VideosDTO videos;

    private CreditsDTO credits;


    public MovieDTO(boolean adult, String backdrop_path, GenreDTO[] genres, Integer id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, String title, VideosDTO video, CreditsDTO credits) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genres = genres;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.videos = video;
        this.credits = credits;
    }

    public MovieDTO() {
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

    public GenreDTO[] getGenres() {
        return genres;
    }

    public void setGenres(GenreDTO[] genres) {
        this.genres = genres;
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

    public VideosDTO getVideos() {
        return videos;
    }

    public void setVideos(VideosDTO videos) {
        this.videos = videos;
    }

    public CreditsDTO getCredits() {
        return credits;
    }

    public void setCredits(CreditsDTO credits) {
        this.credits = credits;
    }


}

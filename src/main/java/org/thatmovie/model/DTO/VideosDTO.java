package org.thatmovie.model.DTO;

import java.util.List;

public class VideosDTO {


    private List<VideoDTO> results;


    public VideosDTO(List<VideoDTO> results) {
        this.results = results;
    }


    public List<VideoDTO> getResults() {
        return results;
    }

    public void setResults(List<VideoDTO> results) {
        this.results = results;
    }
}

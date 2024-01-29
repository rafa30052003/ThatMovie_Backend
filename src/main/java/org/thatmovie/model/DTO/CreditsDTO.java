package org.thatmovie.model.DTO;

import java.util.List;

public class CreditsDTO {
    private int id;
    private List<CastDTO> cast;

    public CreditsDTO(int id, List<CastDTO> cast) {
        this.id = id;
        this.cast = cast;
    }

    public CreditsDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CastDTO> getCast() {
        return cast;
    }

    public void setCast(List<CastDTO> cast) {
        this.cast = cast;
    }
}



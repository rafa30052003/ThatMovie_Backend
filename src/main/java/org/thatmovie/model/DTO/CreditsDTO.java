package org.thatmovie.model.DTO;

import java.util.List;

public class CreditsDTO {
    private int id;
    private List<CastDTO> cast;
    private List<CrewDTO> crew;

    public CreditsDTO(int id, List<CastDTO> cast, List<CrewDTO> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
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

    public List<CrewDTO> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewDTO> crew) {
        this.crew = crew;
    }
}



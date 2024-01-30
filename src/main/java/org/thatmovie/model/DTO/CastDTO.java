package org.thatmovie.model.DTO;

public class CastDTO {

    private String name;

    public CastDTO( String name) {

        this.name = name;
    }

    public CastDTO() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

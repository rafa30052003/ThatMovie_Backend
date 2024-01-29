package org.thatmovie.model.DTO;

public class CastDTO {
    private int id;
    private String name;

    public CastDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CastDTO() {
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
}

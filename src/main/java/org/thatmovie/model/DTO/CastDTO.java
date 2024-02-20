package org.thatmovie.model.DTO;

public class CastDTO {

    private String name;
    private String profile_path;

    private String character;

    public CastDTO(String name, String profile_path, String character) {
        this.name = name;
        this.profile_path = profile_path;
        this.character = character;
    }

    public CastDTO() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}

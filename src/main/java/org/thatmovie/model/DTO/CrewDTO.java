package org.thatmovie.model.DTO;

public class CrewDTO {

    private String name;
    private String job;

    public CrewDTO( String name, String job) {

        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

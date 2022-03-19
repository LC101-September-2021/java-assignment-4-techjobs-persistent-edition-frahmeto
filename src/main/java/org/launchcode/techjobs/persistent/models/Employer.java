package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

//part2, updating models
@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Location is required")
    @Size(max = 150)
    private String location;

    //part#3, adding job field to employer
    @OneToMany
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();

    //No- arg constructor
    public Employer(){}

    //constructor
    public Employer(String location) {
        super();
        this.location = location;
    }

    //Getter && Setter
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

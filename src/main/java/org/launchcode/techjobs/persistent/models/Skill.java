package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

//part#2 adding description
    @NotNull(message = "Please Enter a description.")
    @Size(max = 255, message = "Description must be under 255 characters.")
    private String description;

//part#4 setting up a many-many r/ship
    @ManyToMany(mappedBy = "skills")
    private  List<Job> jobs = new ArrayList<>();

    //No-arg constructor
    public Skill(){}


//    public Skill(String description) {
//        super();
//        this.description = description;
//    }

    public List<Job> getJobs(){
        return jobs;
    }


    public void setJobs(List<Job> jobs){
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
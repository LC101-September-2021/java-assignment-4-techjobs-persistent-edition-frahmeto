package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //part#3, 4 Updating HomeController, adding repository
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;


    //part#3 updating
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    //part#3, 4 updating employers and skills
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
       // model.addAttribute("jobs", jobRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam(required=false) List<Integer> skills) {

        if (errors.hasErrors() ||  (skills==null))  {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
        Employer  employer = employerRepository.findById(employerId).orElse(new Employer());
        newJob.setEmployer(employer);

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Job job = jobRepository.findById(jobId).orElse(new Job());
        model.addAttribute("title", "Job");
        model.addAttribute("job", job);
        return "view";

    }
}



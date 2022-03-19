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
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //part#3 Updating HomeController
    //adding repository
    @Autowired
    private EmployerRepository employerRepository;

    // part#4 , adding repository
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;


    //part#3 updating
    @RequestMapping("")
    public String index(Model model) {
       // model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    //part#3, 4 updating employers and skills
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        //model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId,
                                        @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            model.addAttribute("employers", employerRepository.findAll());
//            model.addAttribute("skills", skillRepository.findAll());

            return "add";
        }

//       // Optional<Employer> optEmployer = employerRepository.findById(employerId);
//        if(optEmployer.isPresent()){
//                Employer employer = optEmployer.get();
//                newJob.setEmployer(employer);
//            }
       Employer newEmployer = employerRepository.findById(employerId).orElse(new Employer());
        newJob.setEmployer(newEmployer);
       // jobRepository.save(newJob);


        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);
        //model.addAttribute("job", jobRepository.findAll());

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optJob = jobRepository.findById(jobId);
        if (!optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:./";
        }
    }
}













// model.addAttribute("title", "Add Job");
//         model.addAttribute("jobs", jobRepository.findAll());
//         model.addAttribute("employers", employerRepository.findAll());
//         model.addAttribute("skills", skillRepository.findAll());
//         model.addAttribute(new Job());

//
//newJob.setEmployer(optEmployer.get());
//        List<Skill> skillsObjs = (List<Skill>)skillRepository.findAllById(skills);
//        newJob.setSkills(skillsObjs);
//        model.addAttribute("job", newJob);
//        jobRepository.save(newJob);

//model.addAttribute("employers", employerRepository.findAll());
//model.addAttribute("skills",skillRepository.findAll());


//@Controller
//public class HomeController {
//
//    @RequestMapping("")
//    public String index(Model model) {
//
//        model.addAttribute("title", "My Jobs");
//
//        return "index";
//    }
//
//    @GetMapping("add")
//    public String displayAddJobForm(Model model) {
//        model.addAttribute("title", "Add Job");
//        model.addAttribute(new Job());
//        return "add";
//    }
//
//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        }
//
//        return "redirect:";
//    }
//
//    @GetMapping("view/{jobId}")
//    public String displayViewJob(Model model, @PathVariable int jobId) {
//
//        return "view";
//    }
//
//
//}
//List jobs = (List<Job>) jobRepository.findAll();
//        model.addAttribute("jobs", jobs);
//List employers = (List<Employer>) employerRepository.findAll();
//        model.addAttribute("employers", employers);

//                List skills = (List<Skill>) skillRepository.findAll();
//        model.addAttribute("skills", skills);


//    List employers = (List<Employer>) employerRepository.findAll();
//            model.addAttribute("employers", employers);


//model.addAttribute("job", jobRepository.findAll());
//        if (jobId == 0) {
//        model.addAttribute("job", jobRepository.findAll());
//    } else {
//        Optional<Job> optJob;
//        optJob = jobRepository.findById(jobId);
//
//        if (optJob.isPresent()) {
//            model.addAttribute("title", "Job: " + optJob.get().getName());
//        }
//        model.addAttribute("job", optJob.get());
//        return "view";
//    }
//        return "redirect:";
//
//}

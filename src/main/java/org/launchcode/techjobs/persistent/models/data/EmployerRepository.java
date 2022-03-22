package org.launchcode.techjobs.persistent.models.data;


import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//todo part2,dataLayer#1
@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}

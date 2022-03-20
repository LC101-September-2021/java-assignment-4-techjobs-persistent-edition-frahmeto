-- Part 1: Test it with SQL
--list the columns and their data types in the table.
SELECT column_name,data_type
FROM information_schema.columns
WHERE table_schema ='techjobs'
AND  table_name ='job';

-- Part 2: Test it with SQL
--write a query to list the names of the employers in St. Louis City.
SELECT name
FROM employer
--Where Location = "saint louis";
Where Location = "St. Louis City";

-- Part 3: Test it with SQL
--write the SQL statement to remove the job table.
DROP TABLE job;

-- Part 4: Test it with SQL

SELECT *
FROM skill INNER JOIN
    job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;
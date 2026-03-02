package ex3;

import java.util.List;
import java.util.Objects;

public class Candidate {
    private String name;
    private int yearsOfExperience;
    private List<String> skills;

    public Candidate(String name, int yearsOfExperience, List<String> skills) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
        this.skills = skills;
    }

    public Candidate() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return yearsOfExperience == candidate.yearsOfExperience && Objects.equals(name, candidate.name) && Objects.equals(skills, candidate.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearsOfExperience, skills);
    }
}

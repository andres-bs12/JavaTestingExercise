package ex3;

import java.util.List;

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
}

package ex3;

import java.util.ArrayList;
import java.util.List;

public class RecruitmentService {

    List<String> getQualifiedCandidates(List<Candidate> candidates, String requiredSkill) {
        if (candidates.isEmpty()) {
            throw new IllegalArgumentException("Candidates list must not be empty");
        }
        if (requiredSkill.isEmpty()) {
            throw new IllegalArgumentException("Required skill can't be empty");
        }

        List<String> fitCandidates = new ArrayList<>();
        for (Candidate candidate : candidates) {
            // Check more than 3 years experience
            if (candidate.getYearsOfExperience() >= 3) {
                for (String skill : candidate.getSkills()) {
                    if (skill.equalsIgnoreCase(requiredSkill)) {
                        fitCandidates.add(candidate.getName());
                        break;
                    }
                }
            }
        }
        return fitCandidates;
    }

}

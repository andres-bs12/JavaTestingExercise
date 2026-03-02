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

    List<String> getScoredCandidates(List<Candidate> candidates, List<String> requiredSkills, int minScore, int yearsOfExperience) {
        if (candidates.isEmpty()) {
            throw new IllegalArgumentException("Candidates list must not be empty");
        }
        if (requiredSkills.isEmpty()) {
            throw new IllegalArgumentException("Required skill can't be empty");
        }
        // Initialize List for fitCandidates
        List<String> fitCandidates = new ArrayList<>();
        // Initialize List to save scores

        // Send candidates to calculate score
        for (Candidate candidate : candidates) {
            if (candidate.getYearsOfExperience() >= yearsOfExperience) {
                int score = calculateScore(candidate);
                if (score > minScore) {
                    fitCandidates.add(String.format((candidate.getName()) + " Score [" + score + "]"));
                }
            }
        }
        return fitCandidates;
    }

    int calculateScore(Candidate candidate) {
        // Initialize counter
        int score = 0;

        score += candidate.getYearsOfExperience() * 10;

        for (String skill : candidate.getSkills()) {
            if (candidate.getSkills().contains(skill)) {
                score += 5;
            }
        }
        return score;
    }

}

package ex3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Candidate candidate1 = new Candidate("John", 5, List.of("Java", "SQL"));
        Candidate candidate2 = new Candidate("Lisa", 2, List.of("Java", "Python"));

        List<Candidate> candidateList = new ArrayList<>(List.of(candidate1, candidate2));

        RecruitmentService service = new RecruitmentService();

        List<String> qualifiedCandidates = service.getQualifiedCandidates(candidateList, "Java");

        System.out.println("These are the qualified candidates: ");
        for (String candidate : qualifiedCandidates) {
            System.out.println(candidate);
        }

    }

}

package ex3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecruitmentServiceTest {

    @Test
    void shouldThrowExceptionWhenCandidatesIsEmpty() {
        List<Candidate> candidateList = new ArrayList<>(List.of());
        RecruitmentService service = new RecruitmentService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.getQualifiedCandidates(candidateList, "Java");
        });
    }

    @Test
    void shouldThrowExceptionWhenRequiredSkillIsEmpty() {
        Candidate candidate = new Candidate("Andres", 2, List.of("Java"));
        List<Candidate> candidateList = new ArrayList<>(List.of(candidate));

        RecruitmentService service = new RecruitmentService();

        assertThrows(IllegalArgumentException.class, () -> {
                    service.getQualifiedCandidates(candidateList, "");
                });
    }


    @Test
    void ShouldNotAddCandidatesWithLessThanThreeYearsExperience() {
        Candidate candidate = new Candidate("Andres", 1, List.of("Java"));
        List<Candidate> candidateList = new ArrayList<>(List.of(candidate));

        RecruitmentService service = new RecruitmentService();

        assertEquals(0, service.getQualifiedCandidates(candidateList, "Java").size());
    }

    @Test
    void ShouldNotAddCandidatesIfSkillsDontMatch() {
        Candidate candidate = new Candidate("Andres", 4, List.of("Python"));
        List<Candidate> candidateList = new ArrayList<>(List.of(candidate));

        RecruitmentService service = new RecruitmentService();

        assertEquals(0, service.getQualifiedCandidates(candidateList, "Java").size());
    }

    @Test
    void shouldReturnAListWithFitCandidates() {
        Candidate candidate = new Candidate("Andres", 4, List.of("Java"));
        List<Candidate> candidateList = new ArrayList<>(List.of(candidate));

        RecruitmentService service = new RecruitmentService();

        assertEquals(1, service.getQualifiedCandidates(candidateList, "Java").size());
    }

}

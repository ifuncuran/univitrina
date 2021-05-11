package ru.hh.univitrina.dao;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createProfession;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialty;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createTrainingDirection;


public class ProfessionDaoTest extends UnivitrinaTestBase {

    @Inject
    private ProfessionDao professionDao;

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer PAGE = 1;
    private static final Integer DEFAULT_PER_PAGE = 20;
    private static final Integer PER_PAGE = 2;

    @BeforeEach
    private void init() {
        insertData();
    }

    @Test
    public void getSearchSuggestionTest() {
        List<Profession> expectedProfessions = getExpectedList();
        List<Profession> actualProfessions = getSearchSuggestion("pr", 5);

        assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
    }

    @Test
    public void getSearchSuggestionLimitTest() {
        List<Profession> expectedProfessions = getExpectedList().subList(0, 2);
        List<Profession> actualProfessions = getSearchSuggestion("pr", 2);

        assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
    }

    @Test
    public void getFilteredByHhapiId() {
        List<Profession> expectedProfessions = getExpectedList();
        List<Profession> actualProfessions = getFilteredByHhapiId(DEFAULT_PAGE, DEFAULT_PER_PAGE, "1");

        assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
    }

    @Test
    public void getFilteredByHhapiIdWithPaginationTest() {
        List<Profession> expectedProfessions = getExpectedList().subList(2, 4);
        List<Profession> actualProfessions = getFilteredByHhapiId(PAGE, PER_PAGE, "1");

        assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
    }

    @Test
    public void getFilteredBySpecialtyTest() {
        List<Profession> expectedProfessions = getExpectedList();
        List<Profession> actualProfessions = getFilteredBySpecialty(DEFAULT_PAGE, DEFAULT_PER_PAGE, 1);

        assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
    }

    @Test
    public void getFilteredBySpecialtyWithPaginationTest() {
        List<Profession> expectedProfessions = getExpectedList().subList(2, 4);
        List<Profession> actualProfessions = getFilteredBySpecialty(PAGE, PER_PAGE, 1);

        assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
    }

    private void insertData() {
        Section section1 = new Section("Section1");
        Section section2 = new Section("Section2");

        TrainingDirection tDirection1 = createTrainingDirection("tDirection1", "tDirectionCode1", section1);
        TrainingDirection tDirection2 = createTrainingDirection("tDirection2", "tDirectionCode2", section2);

        Specialty specialty1 = createSpecialty("Specialty1", "spCode1", tDirection1);
        Specialty specialty2 = createSpecialty("Specialty2", "spCode2", tDirection2);
        Specialty specialty3 = createSpecialty("Specialty3", "spCode3", tDirection2);

        Profession profession0 = createProfession("porf0", "0", Set.of(specialty2, specialty3));
        Profession profession1 = createProfession("PROFGF", "1", Set.of(specialty1, specialty3));
        Profession profession2 = createProfession("pRoFg", "1", Set.of(specialty1, specialty2));
        Profession profession3 = createProfession("prof3", "1", Set.of(specialty1, specialty3));
        Profession profession4 = createProfession("prof4", "1", Set.of(specialty1, specialty2));
        Profession profession5 = createProfession("p r o f", "2", Set.of(specialty2, specialty3));
        Profession profession6 = createProfession(" prof ", "2", Set.of(specialty2, specialty3));
        Profession profession7 = createProfession("p rof", "2", Set.of(specialty2, specialty3));
        saveObjects(section1, section2, tDirection1, tDirection2, specialty1, specialty2, specialty3,
                profession0, profession1, profession2, profession3,
                profession4, profession5, profession6, profession7);
    }

    @NotNull
    private List<Profession> getSearchSuggestion(String prefix, int limit) {
        return transactionalScope.read(() -> professionDao.getSearchSuggestion(prefix, limit));
    }

    @NotNull
    private List<Profession> getFilteredByHhapiId(int page, int perPage, String hhapiId) {
        return transactionalScope.read(() -> professionDao.getFilteredByHhapiId(page, perPage, hhapiId));
    }

    @NotNull
    private List<Profession> getFilteredBySpecialty(int page, int perPage, int specialtyId) {
        return transactionalScope.read(() -> professionDao.getFilteredBySpecialty(page, perPage, specialtyId));
    }

    @NotNull
    private List<Profession> getExpectedList() {
        Profession profession1 = createProfession(2, "PROFGF", "1");
        Profession profession2 = createProfession(3, "pRoFg", "1");
        Profession profession3 = createProfession(4, "prof3", "1");
        Profession profession4 = createProfession(5, "prof4", "1");
        return List.of(profession1, profession2, profession3, profession4);
    }
}

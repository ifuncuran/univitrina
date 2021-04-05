package ru.hh.univitrina.dao;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniversityDaoTest extends UnivitrinaTestBase {

  @Inject
  private UniversityDao universityDao;

  private static final Integer DEFAULT_PAGE = 0;
  private static final Integer PAGE = 1;
  private static final Integer DEFAULT_PER_PAGE = 20;
  private static final Integer PER_PAGE = 2;

  @BeforeEach
  private void init() {
    insertData();
  }

  @Test
  public void getByIdTest() {
    University expectedUniversity = createUniversity(1, "unA1", 1);
    University actualUniversity = getUniversityById(1);
    assertTrue(equalUniversities(expectedUniversity, actualUniversity));
  }

  @Test
  public void getFilteredByAreaTest() {
    List<University> expectedUniversities = getExpectedList();
    List<University> actualUniversities = getFilteredByArea(DEFAULT_PAGE, DEFAULT_PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredByAreaWithPaginationTest() {
    List<University> expectedUniversities = getExpectedList().subList(2, 4);
    List<University> actualUniversities = getFilteredByArea(PAGE, PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredBySpecialtyTest() {
    List<University> expectedUniversities = getExpectedList();
    List<University> actualUniversities = getFilteredBySpecialty(DEFAULT_PAGE, DEFAULT_PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredBySpecialtyWithPaginationTest() {
    List<University> expectedUniversities = getExpectedList().subList(2, 4);
    List<University> actualUniversities = getFilteredBySpecialty(PAGE, PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredByTrainingDirectionTest() {
    List<University> expectedUniversities = getExpectedList();
    List<University> actualUniversities = getFilteredByTrainingDirection(DEFAULT_PAGE, DEFAULT_PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredByTrainingDirectionWithPaginationTest() {
    List<University> expectedUniversities = getExpectedList().subList(2, 4);
    List<University> actualUniversities = getFilteredByTrainingDirection(PAGE, PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredBySectionTest() {
    List<University> expectedUniversities = getExpectedList();
    List<University> actualUniversities = getFilteredBySection(DEFAULT_PAGE, DEFAULT_PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  @Test
  public void getFilteredBySectionWithPaginationTest() {
    List<University> expectedUniversities = getExpectedList().subList(2, 4);
    List<University> actualUniversities = getFilteredBySection(PAGE, PER_PAGE, 1);
    assertEqualsUniversityList(expectedUniversities, actualUniversities);
  }

  private void insertData() {
    Section section1 = new Section("Section1");
    Section section2 = new Section("Section2");

    TrainingDirection tDirection1 = createTrainingDirection("tDirection1", "tDirectionCode1", section1);
    TrainingDirection tDirection2 = createTrainingDirection("tDirection2", "tDirectionCode2", section2);

    Specialty specialty1 = createSpecialty("Specialty1", "spCode1", tDirection1);
    Specialty specialty2 = createSpecialty("Specialty2", "spCode2", tDirection2);
    Specialty specialty3 = createSpecialty("Specialty3", "spCode3", tDirection2);

    University university1 = createUniversity("unA1", 1, Set.of(specialty1, specialty2));
    University university2 = createUniversity("unA2", 1, Set.of(specialty1, specialty3));
    University university3 = createUniversity("unA3", 1, Set.of(specialty1, specialty2));
    University university4 = createUniversity("unA4", 1, Set.of(specialty1, specialty2));
    University university5 = createUniversity("unB1", 2, Set.of(specialty2, specialty3));
    University university6 = createUniversity("unB2", 2, Set.of(specialty2, specialty3));
    University university7 = createUniversity("unB3", 2, Set.of(specialty2, specialty3));

    saveObjects(section1, section2, tDirection1, tDirection2, specialty1, specialty2, specialty3,
        university1, university2, university3, university4, university5, university6, university7);
  }

  private University getUniversityById(int id) {
    return transactionalScope.read(() -> universityDao.getById(University.class, id));
  }

  private List<University> getFilteredByArea(int page, int perPage, int areaId) {
    return transactionalScope.read(() -> universityDao.getFilteredByArea(page, perPage, areaId));
  }

  private List<University> getFilteredBySpecialty(int page, int perPage, int specialtyId) {
    return transactionalScope.read(() -> universityDao.getFilteredBySpecialty(page, perPage, specialtyId));
  }

  private List<University> getFilteredByTrainingDirection(int page, int perPage, int tDirectionId) {
    return transactionalScope.read(() -> universityDao.getFilteredByTrainingDirection(page, perPage, tDirectionId));
  }

  private List<University> getFilteredBySection(int page, int perPage, int sectionId) {
    return transactionalScope.read(() -> universityDao.getFilteredBySection(page, perPage, sectionId));
  }

  private List<University> getExpectedList() {
    University university1 = createUniversity(1, "unA1", 1);
    University university2 = createUniversity(2, "unA2", 1);
    University university3 = createUniversity(3, "unA3", 1);
    University university4 = createUniversity(4, "unA4", 1);
    return List.of(university1, university2, university3, university4);
  }

  private TrainingDirection createTrainingDirection(String name, String code, Section section) {
    TrainingDirection tDirection = new TrainingDirection(name, code);
    tDirection.setSection(section);
    return tDirection;
  }

  private Specialty createSpecialty(String name, String code, TrainingDirection trainingDirection) {
    Specialty specialty = new Specialty(name, code);
    specialty.setTrainingDirection(trainingDirection);
    return specialty;
  }

  private University createUniversity(Integer id, String name, Integer areaId) {
    University university = new University(name, areaId);
    university.setId(id);
    return university;
  }

  private University createUniversity(String name, Integer areaId, Set<Specialty> specialtySet) {
    University university = new University(name, areaId);
    university.setSpecialtySet(specialtySet);
    return university;
  }

  private boolean equalUniversities(University u1, University u2) {
    return Objects.equals(u1.getId(), u2.getId())
        && Objects.equals(u1.getName(), u2.getName())
        && Objects.equals(u1.getAreaId(), u2.getAreaId());
  }

  private boolean contains(List<University> universityList, University university) {
    return universityList.stream()
        .anyMatch(u -> equalUniversities(u, university));
  }

  private void assertEqualsUniversityList(List<University> expectedList, List<University> actualList) {
    assertEquals(expectedList.size(), actualList.size(), "Size of UniversityLists not equal: ");
    expectedList.forEach(university -> assertTrue(contains(actualList, university)));
  }
}

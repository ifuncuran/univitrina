package ru.hh.univitrina.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;

import java.util.List;
import java.util.Set;

import static ru.hh.univitrina.dao.CreateObjectsUtil.createProfession;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialty;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createTrainingDirection;

public class SpecialtyResourceTest extends UnivitrinaTestBase {

  private static final String SPECIALTY_URL = "/specialties/professionId";
  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  private void init() {
    insertData();
  }

  @Test
  public void getFilteredByProfessionTest() throws JsonProcessingException {
    resourceHelper.assertGet(SPECIALTY_URL + "/1?page=0&per_page=10", getExpectedResponse());
  }

  @Test
  public void getFilteredByProfessionSecondPageTest() throws JsonProcessingException {
    resourceHelper.assertGet(SPECIALTY_URL + "/1?page=1&per_page=2", getExpectedSecondPageResponse());
  }

  @Test
  public void getFilteredByProfessionEmptyTest() throws JsonProcessingException {
    resourceHelper.assertGet(SPECIALTY_URL + "/9?page=0&per_page=10", "[]");
  }

  private void insertData() {
    Section section1 = new Section("S1");
    Section section2 = new Section("S2");

    TrainingDirection tDirection1 = createTrainingDirection("tDir1", "tDirC1", section1);
    TrainingDirection tDirection2 = createTrainingDirection("tDir2", "tDirC2", section2);

    Specialty specialty1 = createSpecialty("Sp1", "SpC1", tDirection1);
    Specialty specialty2 = createSpecialty("Sp2", "SpC2", tDirection2);
    Specialty specialty3 = createSpecialty("Sp3", "SpC3", tDirection1);
    Specialty specialty4 = createSpecialty("Sp4", "SpC4", tDirection2);
    Specialty specialty5 = createSpecialty("Sp5", "SpC5", tDirection1);
    Specialty specialty6 = createSpecialty("Sp6", "SpC6", tDirection2);
    Specialty specialty7 = createSpecialty("Sp7", "SpC7", tDirection1);

    Profession profession0 = createProfession("Prof1", "0", Set.of(specialty1, specialty2, specialty3, specialty4, specialty6));
    Profession profession1 = createProfession("Prof2", "1", Set.of(specialty3, specialty5));
    Profession profession2 = createProfession("Prof3", "1", Set.of(specialty1, specialty2, specialty3));
    Profession profession3 = createProfession("Prof4", "1", Set.of(specialty4, specialty6));

    saveObjects(section1, section2, tDirection1, tDirection2,
        specialty1, specialty2, specialty3, specialty4,
        specialty5, specialty6, specialty7, profession0,
        profession1, profession2, profession3);
  }

  private String getExpectedResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialties());
  }

  private String getExpectedSecondPageResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialties().subList(2, 4));
  }

  private List<SpecialtyDto> getSpecialties(){
    SpecialtyDto specialty1 = new SpecialtyDto(1, "Sp1", "SpC1", null);
    SpecialtyDto specialty2 = new SpecialtyDto(2, "Sp2", "SpC2", null);
    SpecialtyDto specialty3 = new SpecialtyDto(3, "Sp3", "SpC3", null);
    SpecialtyDto specialty4 = new SpecialtyDto(4, "Sp4", "SpC4", null);
    SpecialtyDto specialty6 = new SpecialtyDto(6, "Sp6", "SpC6", null);

    return List.of(specialty1, specialty2, specialty3, specialty4, specialty6);
  }

}

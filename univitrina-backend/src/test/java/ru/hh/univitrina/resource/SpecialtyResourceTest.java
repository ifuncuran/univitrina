package ru.hh.univitrina.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.dto.TrainingDirectionDto;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;

import java.util.List;
import java.util.Set;

import static ru.hh.univitrina.dao.CreateObjectsUtil.createProfession;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialty;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialtyDto;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createTrainingDirection;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createUniversity;

public class SpecialtyResourceTest extends UnivitrinaTestBase {

  private static final String PROFESSION_ID_URL = "/specialties/professionId";
  private static final String SPECIALTY_URL = "/specialties";
  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  private void init() {
    insertData();
  }

//  @Test
//  public void getSpecialtyByIdTest() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "/1", getExpectedResponseById());
//  }

  @Test
  public void getFilteredByProfessionTest() throws JsonProcessingException {
    resourceHelper.assertGet(PROFESSION_ID_URL + "/1?page=0&per_page=10", getExpectedResponse());
  }

  @Test
  public void getFilteredByProfessionSecondPageTest() throws JsonProcessingException {
    resourceHelper.assertGet(PROFESSION_ID_URL + "/1?page=1&per_page=2", getExpectedSecondPageResponse());
  }

  @Test
  public void getFilteredByProfessionEmptyTest() throws JsonProcessingException {
    resourceHelper.assertGet(PROFESSION_ID_URL + "/9?page=0&per_page=10", "[]");
  }

//  @Test
//  public void getSpecialtyListByText() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "?text=p2", getExpectedSpecialtyListByText());
//  }

//  @Test
//  public void getSpecialtyListByTrainingDirection() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "?training_direction=2", getExpectedSpecialtyListByTrainingDirection());
//  }

//  @Test
//  public void getSpecialtyListBySection() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "?section=2", getExpectedSpecialtyListBySection());
//  }

//  @Test
//  public void getSpecialtyListByProfession() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "?profession=3", getExpectedSpecialtyListByProfession());
//  }

//  @Test
//  public void getSpecialtyListByUniversity() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "?university=3", getExpectedSpecialtyListByUniversity());
//  }

//  @Test
//  public void getSpecialtyListByProfessionList() throws JsonProcessingException {
//    resourceHelper.assertGet(SPECIALTY_URL + "?profession=3", getExpectedSpecialtyListByProfessionList());
//  }

  private void insertData() {
    Section section1 = new Section("S1");
    Section section2 = new Section("S2");

    TrainingDirection tDirection1 = createTrainingDirection("tDir1", "tDirC1", section1);
    TrainingDirection tDirection2 = createTrainingDirection("tDir2", "tDirC2", section2);

    Specialty specialty1 = createSpecialty(" SP22", "SpC2", tDirection2);
    Specialty specialty2 = createSpecialty("S p2", "SpC3", tDirection2);
    Specialty specialty3 = createSpecialty("Sp1", "SpC1", tDirection2);
    Specialty specialty4 = createSpecialty("Sp3 2", "SpC4", tDirection1);
    Specialty specialty5 = createSpecialty("Sp5", "SpC5", tDirection1);
    Specialty specialty6 = createSpecialty("Sp6", "SpC6", tDirection1);
    Specialty specialty7 = createSpecialty("Sp7", "SpC7", tDirection1);


    Profession profession0 = createProfession("Prof1", "0", Set.of(specialty1, specialty2, specialty3, specialty4, specialty6));
    Profession profession1 = createProfession("Prof2", "1", Set.of(specialty3, specialty5));
    Profession profession2 = createProfession("Prof3", "1", Set.of(specialty1, specialty2, specialty3));
    Profession profession3 = createProfession("Prof4", "1", Set.of(specialty4, specialty6));

    University university0 = createUniversity("unA1", 1,  "dis", "exDis", Set.of(specialty1, specialty2, specialty3, specialty4, specialty6));
    University university1 = createUniversity("unA2", 2,  "dis", "exDis", Set.of(specialty1, specialty3));
    University university2 = createUniversity("unB3", 2,  "dis", "exDis", Set.of(specialty1, specialty2, specialty3));
    University university3 = createUniversity("unb4", 1,  "dis", "exDis", Set.of(specialty5, specialty6));


    saveObjects(section1, section2, tDirection1, tDirection2,
        specialty1, specialty2, specialty3, specialty4, specialty5,
        specialty6, specialty7, profession0, profession1,
        profession2, profession3, university0, university1,
        university2, university3);
  }

  private String getExpectedResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialties());
  }

  private String getExpectedResponseById() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtyDto());
  }

  private String getExpectedSecondPageResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialties().subList(2, 4));
  }

  private String getExpectedSpecialtyListByText() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtiesDtoToList().subList(0, 2));
  }

  private String getExpectedSpecialtyListByTrainingDirection() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtiesDtoToList().subList(0, 3));
  }

  private String getExpectedSpecialtyListBySection() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtiesDtoToList().subList(0, 3));
  }

  private String getExpectedSpecialtyListByProfession() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtiesDtoToList().subList(0, 3));
  }

  private String getExpectedSpecialtyListByUniversity() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtiesDtoToList().subList(0, 3));
  }

  private String getExpectedSpecialtyListByProfessionList() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtiesDtoToList().subList(0, 3));
  }

  private List<SpecialtyDto> getSpecialtiesDtoToList(){
    TrainingDirectionDto tDirectionDto1 = new TrainingDirectionDto(1, "tDir1", "tDirC1");
    TrainingDirectionDto tDirectionDto2 = new TrainingDirectionDto(2, "tDir2", "tDirC2");

    ProfessionDto profession1 = new ProfessionDto(1, "Prof1");
    ProfessionDto profession2 = new ProfessionDto(2, "Prof2");
    ProfessionDto profession3 = new ProfessionDto(3, "Prof3");
    ProfessionDto profession4 = new ProfessionDto(4, "Prof4");

    SpecialtyDto specialty1 = createSpecialtyDto(1, " SP22", "SpC2", null, tDirectionDto2, List.of(profession1 , profession3));
    SpecialtyDto specialty2 = createSpecialtyDto(2, "S p2", "SpC3", null, tDirectionDto2, List.of(profession1, profession3));
    SpecialtyDto specialty3 = createSpecialtyDto(3, "Sp1", "SpC1", null, tDirectionDto2, List.of(profession1, profession2, profession3));
    SpecialtyDto specialty4 = createSpecialtyDto(4, "Sp3 2", "SpC4", null, tDirectionDto1, List.of(profession1, profession3));
    SpecialtyDto specialty6 = createSpecialtyDto(6, "Sp6", "SpC6", null, tDirectionDto1, List.of(profession1, profession4));




    return List.of(specialty1, specialty2, specialty3, specialty4, specialty6);
  }

  private SpecialtyDto getSpecialtyDto() {
    ProfessionDto profession1 = new ProfessionDto(1, "Prof1");
    ProfessionDto profession3 = new ProfessionDto(3, "Prof3");
    TrainingDirectionDto tDirectionDto = new TrainingDirectionDto(2, "tDir2", "tDirC2");

    return createSpecialtyDto(1, " SP22", "SpC2", null, tDirectionDto, List.of(profession1 , profession3));
  }

  private List<SpecialtyDto> getSpecialties() {
    SpecialtyDto specialty1 = new SpecialtyDto(1, " SP22", "SpC2", null);
    SpecialtyDto specialty2 = new SpecialtyDto(2, "S p2", "SpC3", null);
    SpecialtyDto specialty3 = new SpecialtyDto(3, "Sp1", "SpC1", null);
    SpecialtyDto specialty4 = new SpecialtyDto(4, "Sp3 2", "SpC4", null);
    SpecialtyDto specialty6 = new SpecialtyDto(6, "Sp6", "SpC6", null);

    return List.of(specialty1, specialty2, specialty3, specialty4, specialty6);
  }

}

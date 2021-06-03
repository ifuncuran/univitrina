package ru.hh.univitrina.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.dto.ProfessionShortDto;
import ru.hh.univitrina.dto.SpecialtyShortDto;
import ru.hh.univitrina.dto.UniversityShortDto;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createProfession;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialty;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createTrainingDirection;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createUniversity;

class SuggestResourceTest extends UnivitrinaTestBase {
  private static final String UNIVERSITY_SUGGEST_URL = "/search/suggest/university";
  private static final String SPECIALTY_SUGGEST_URL = "/search/suggest/specialty";
  private static final String PROFESSION_SUGGEST_URL = "/search/suggest/profession";
  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  private void init() {
    insertData();
  }

  @Test
  void getUniversitySuggest() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_SUGGEST_URL + "?prefix=una", getExpectedUniversityResponse());
  }

  @Test
  void getUniversitySuggestWithLimit() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_SUGGEST_URL + "?prefix=una&limit=2", getExpectedUniversityResponseWithLimit());
  }

  @Test
  void getUniversitySuggestWithZeroLimit() {
    resourceHelper.assertGet(UNIVERSITY_SUGGEST_URL + "?prefix=una&limit=0", "[]");
  }

  @Test
  void getUniversitySuggestWithEmptyQuery() {
    Response response = resourceHelper.executeGet(UNIVERSITY_SUGGEST_URL);
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getUniversitySuggestWithEmptyPrefix() {
    Response response = resourceHelper.executeGet(UNIVERSITY_SUGGEST_URL + "?prefix=%20");
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getUniversitySuggestWithLimitLessThanZero() {
    Response response = resourceHelper.executeGet(UNIVERSITY_SUGGEST_URL + "?prefix=una&limit=-2");
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getSpecialtySuggest() throws JsonProcessingException {
    resourceHelper.assertGet(SPECIALTY_SUGGEST_URL + "?prefix=spa", getExpectedSpecialtyResponse());
  }

  @Test
  void getSpecialtySuggestWithLimit() throws JsonProcessingException {
    resourceHelper.assertGet(SPECIALTY_SUGGEST_URL + "?prefix=spa&limit=2", getExpectedSpecialtyResponseWithLimit());
  }

  @Test
  void getSpecialtySuggestWithZeroLimit() {
    resourceHelper.assertGet(SPECIALTY_SUGGEST_URL + "?prefix=spa&limit=0", "[]");
  }

  @Test
  void getSpecialtySuggestWithEmptyQuery() {
    Response response = resourceHelper.executeGet(SPECIALTY_SUGGEST_URL);
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getSpecialtySuggestWithEmptyPrefix() {
    Response response = resourceHelper.executeGet(SPECIALTY_SUGGEST_URL + "?prefix=%20");
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getSpecialtySuggestWithLimitLessThanZero() {
    Response response = resourceHelper.executeGet(SPECIALTY_SUGGEST_URL + "?prefix=spa&limit=-2");
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getProfessionSuggest() throws JsonProcessingException {
    resourceHelper.assertGet(PROFESSION_SUGGEST_URL + "?prefix=pr", getExpectedProfessionResponse());
  }

  @Test
  void getProfessionSuggestWithLimit() throws JsonProcessingException {
    resourceHelper.assertGet(PROFESSION_SUGGEST_URL + "?prefix=pr&limit=2", getExpectedProfessionResponseWithLimit());
  }

  @Test
  void getProfessionSuggestWithZeroLimit() {
    resourceHelper.assertGet(PROFESSION_SUGGEST_URL + "?prefix=pr&limit=0", "[]");
  }

  @Test
  void getProfessionSuggestWithEmptyQuery() {
    Response response = resourceHelper.executeGet(PROFESSION_SUGGEST_URL);
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getProfessionSuggestWithEmptyPrefix() {
    Response response = resourceHelper.executeGet(PROFESSION_SUGGEST_URL + "?prefix=%20");
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  void getProfessionSuggestWithLimitLessThanZero() {
    Response response = resourceHelper.executeGet(PROFESSION_SUGGEST_URL + "?prefix=pr&limit=-2");
    assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  private void insertData() {
    Section section1 = new Section("Section1");
    Section section2 = new Section("Section2");

    TrainingDirection tDirection1 = createTrainingDirection("tDirection1", "tDirectionCode1", section1);
    TrainingDirection tDirection2 = createTrainingDirection("tDirection2", "tDirectionCode2", section2);

    Specialty specialty1 = createSpecialty("SpA1", "02.03.01", tDirection1);
    Specialty specialty2 = createSpecialty("SpA2", "02.03.02", tDirection1);
    Specialty specialty3 = createSpecialty("SpA3", "02.03.03", tDirection1);
    Specialty specialty4 = createSpecialty("SpB1", "03.03.01", tDirection2);

    University university1 = createUniversity("unA1", 1, Set.of(specialty1, specialty2));
    University university2 = createUniversity("unA2", 1, Set.of(specialty1, specialty3));
    University university3 = createUniversity("unA3", 1, Set.of(specialty1, specialty2));
    University university4 = createUniversity("unA4", 1, Set.of(specialty1, specialty2));
    University university5 = createUniversity("unB1", 2, Set.of(specialty2, specialty3));
    University university6 = createUniversity("unB2", 2, Set.of(specialty2, specialty3));
    University university7 = createUniversity("unB3", 2, Set.of(specialty2, specialty4));

    Profession profession0 = createProfession("PROFGF", "1");
    Profession profession1 = createProfession("pRoFg", "1");
    Profession profession2 = createProfession("profhg", "1");
    Profession profession3 = createProfession("p r o f", "1");
    Profession profession4 = createProfession(" prof ", "1");

    saveObjects(section1, section2, tDirection1, tDirection2, specialty1, specialty2, specialty3, specialty4,
        university1, university2, university3, university4, university5, university6, university7,
        profession0, profession1, profession2, profession3, profession4);
  }

  private String getExpectedUniversityResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList());
  }

  private String getExpectedUniversityResponseWithLimit() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList().subList(0, 2));
  }

  private List<UniversityShortDto> getUniversityDtoList() {
    UniversityShortDto university1 = new UniversityShortDto(1, "unA1");
    UniversityShortDto university2 = new UniversityShortDto(2, "unA2");
    UniversityShortDto university3 = new UniversityShortDto(3, "unA3");
    UniversityShortDto university4 = new UniversityShortDto(4, "unA4");
    return List.of(university1, university2, university3, university4);
  }

  private String getExpectedSpecialtyResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtyDtoList());
  }

  private String getExpectedSpecialtyResponseWithLimit() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getSpecialtyDtoList().subList(0, 2));
  }

  private List<SpecialtyShortDto> getSpecialtyDtoList() {
    SpecialtyShortDto specialty1 = new SpecialtyShortDto(1, "SpA1");
    SpecialtyShortDto specialty2 = new SpecialtyShortDto(2, "SpA2");
    SpecialtyShortDto specialty3 = new SpecialtyShortDto(3, "SpA3");
    return List.of(specialty1, specialty2, specialty3);
  }

  private String getExpectedProfessionResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getProfessionDtoList());
  }

  private String getExpectedProfessionResponseWithLimit() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getProfessionDtoList().subList(0, 2));
  }

  private List<ProfessionShortDto> getProfessionDtoList() {
    ProfessionShortDto profession1 = new ProfessionShortDto(1, "PROFGF");
    ProfessionShortDto profession2 = new ProfessionShortDto(2, "pRoFg");
    ProfessionShortDto profession3 = new ProfessionShortDto(3, "profhg");
    return List.of(profession1, profession2, profession3);
  }
}

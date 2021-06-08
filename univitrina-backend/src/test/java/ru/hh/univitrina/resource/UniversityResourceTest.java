package ru.hh.univitrina.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.dto.TrainingDirectionDto;
import ru.hh.univitrina.dto.UniversityDto;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;
import ru.hh.univitrina.service.AreaService;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialty;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialtyDto;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createTrainingDirection;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createUniversity;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createUniversityDto;

public class UniversityResourceTest extends UnivitrinaTestBase {
  private static final String UNIVERSITY_URL = "/universities";
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  private AreaService areaService;

  @BeforeEach
  private void init() {
    insertData();
  }

  @Test
  public void getUniversityByIdTest() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_URL + "/1", getExpectedUniversityResponse());
  }

  @Test
  public void getUniversityNotFoundTest() {
    Response response = resourceHelper.executeGet(UNIVERSITY_URL + "/99");
    assertEquals(NOT_FOUND.getStatusCode(), response.getStatus());
  }

  @Test
  public void  getAllUniversityList() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_URL, getExpectedAllUniversityList());
  }

  @Test
  public void  getUniversityListByArea() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_URL + "?area=1", getExpectedUniversityListByArea());
  }

  @Test
  public void  getUniversityListBySpecialty() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_URL + "?specialty=3", getExpectedUniversityListBySpecialty());
  }

  @Test
  public void  getUniversityListByText() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_URL + "?text=nb", getExpectedUniversityListByText());
  }

  @Test
  public void  getUniversityListByAreaSpecialtyText() throws JsonProcessingException {
    resourceHelper.assertGet(UNIVERSITY_URL + "?area=1&specialty=3&text=na", getExpectedUniversityListByAreaSpecialtyText());
  }

  @Test
  public void  getUniversityListShoulReturnEmptyList() {
    resourceHelper.assertGet(UNIVERSITY_URL + "?area=1&specialty=2&text=nb", "[]");
  }

  private void insertData() {
    Section section = new Section("Section1");

    TrainingDirection tDirection = createTrainingDirection("tDirection", "tDirectionCode", section);

    Specialty sp1 = createSpecialty("SpA1", "02.03.01", "description1", tDirection);
    Specialty sp2 = createSpecialty("SpA2", "02.03.02", "description2", tDirection);
    Specialty sp3 = createSpecialty("SpA3", "02.03.03", "description3", tDirection);

    University un1 = createUniversity("unA1", 1,
        "description", "extDescription", Set.of(sp1, sp2));

    University un2 = createUniversity("unA2", 1,
        "description", "extDescription", Set.of(sp1, sp3));

    University un3 = createUniversity("unB3", 2, "description", "extDescription", Set.of(sp2, sp3));

    saveObjects(section, tDirection, sp1, sp2, sp3, un1, un2, un3);
  }

  private String getExpectedUniversityResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDto());
  }

  private String getExpectedAllUniversityList() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList());
  }

  private String getExpectedUniversityListByArea() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList().subList(0, 2));
  }

  private String getExpectedUniversityListBySpecialty() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList().subList(1, 3));
  }

  private String getExpectedUniversityListByText() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList().subList(2, 3));
  }

  private String getExpectedUniversityListByAreaSpecialtyText() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDtoList().subList(1, 2));
  }

  private UniversityDto getUniversityDto() {
    TrainingDirectionDto tDirectionDto = new TrainingDirectionDto(1, "tDirection", "tDirectionCode");

    SpecialtyDto sp1 = createSpecialtyDto(1, "SpA1", "02.03.01", "description1", tDirectionDto);
    SpecialtyDto sp2 = createSpecialtyDto(2, "SpA2", "02.03.02", "description2", tDirectionDto);

    String areaName = areaService.getAreaNameById(1);

    return createUniversityDto(1, "unA1", areaName,
        "description", "extDescription", List.of(sp1, sp2));
  }

  private List<UniversityDto> getUniversityDtoList() {
    TrainingDirectionDto tDirectionDto = new TrainingDirectionDto(1, "tDirection", "tDirectionCode");

    SpecialtyDto sp1 = createSpecialtyDto(1, "SpA1", "02.03.01", "description1", tDirectionDto);
    SpecialtyDto sp2 = createSpecialtyDto(2, "SpA2", "02.03.02", "description2", tDirectionDto);
    SpecialtyDto sp3 = createSpecialtyDto(3, "SpA3", "02.03.03", "description3", tDirectionDto);

    String areaName1 = areaService.getAreaNameById(1);
    String areaName2 = areaService.getAreaNameById(2);

    UniversityDto universityDto1 = createUniversityDto(1, "unA1", areaName1,
        "description", "extDescription", List.of(sp1, sp2));

    UniversityDto universityDto2 = createUniversityDto(2, "unA2", areaName1,
        "description", "extDescription", List.of(sp1, sp3));

    UniversityDto universityDto3 = createUniversityDto(3, "unB3", areaName2,
        "description", "extDescription", List.of(sp2, sp3));

    return List.of(universityDto1, universityDto2, universityDto3);
  }
}

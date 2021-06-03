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

  private void insertData() {
    Section section = new Section("Section1");

    TrainingDirection tDirection = createTrainingDirection("tDirection", "tDirectionCode", section);

    Specialty specialty1 = createSpecialty("SpA1", "02.03.01", "description1", tDirection);
    Specialty specialty2 = createSpecialty("SpA2", "02.03.02", "description2", tDirection);

    University university = createUniversity("unA1", 1,
        "description", "extDescription", Set.of(specialty1, specialty2));

    saveObjects(section, tDirection, specialty1, specialty2, university);
  }

  private String getExpectedUniversityResponse() throws JsonProcessingException {
    return objectMapper.writeValueAsString(getUniversityDto());
  }

  private UniversityDto getUniversityDto() {
    TrainingDirectionDto tDirectionDto = new TrainingDirectionDto(1, "tDirection", "tDirectionCode");

    SpecialtyDto sp1 = createSpecialtyDto(1, "SpA1", "02.03.01", "description1", tDirectionDto);
    SpecialtyDto sp2 = createSpecialtyDto(2, "SpA2", "02.03.02", "description2", tDirectionDto);

    String areaName = areaService.getAreaNameById(1);

    return createUniversityDto(1, "unA1", areaName,
        "description", "extDescription", List.of(sp1, sp2));
  }
}

package ru.hh.univitrina.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;

import java.util.List;
import java.util.Set;

import static ru.hh.univitrina.dao.CreateObjectsUtil.createProfession;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createSpecialty;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createTrainingDirection;

class ProfessionResourceTest extends UnivitrinaTestBase {

    private static final String PROFESSION_URL = "/professions/specialty";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void init() {
        insertData();
    }

    @Test
    public void getFilteredBySpecialtyTest() throws JsonProcessingException {
        resourceHelper.assertGet(PROFESSION_URL + "/1?page=0&per_page=10", getExpectedResponse());
    }

    @Test
    public void getFilteredBySpecialtySecondPageTest() throws JsonProcessingException {
        resourceHelper.assertGet(PROFESSION_URL + "/1?page=1&per_page=2", getExpectedSecondPageResponse());
    }

    @Test
    public void getFilteredBySpecialtyEmptyTest() throws JsonProcessingException {
        resourceHelper.assertGet(PROFESSION_URL + "/9?page=0&per_page=10", "[]");
    }

    private void insertData() {
        Section section1 = new Section("S1");
        Section section2 = new Section("S2");

        TrainingDirection tDirection1 = createTrainingDirection("tDir1", "tDirC1", section1);
        TrainingDirection tDirection2 = createTrainingDirection("tDir2", "tDirC2", section2);

        Specialty specialty1 = createSpecialty("Sp1", "SpC1", tDirection1);
        Specialty specialty2 = createSpecialty("Sp2", "SpC2", tDirection2);
        Specialty specialty3 = createSpecialty("Sp3", "SpC3", tDirection2);

        Profession profession0 = createProfession("Prof1", "0", Set.of(specialty1, specialty3));
        Profession profession1 = createProfession("Prof2", "1", Set.of(specialty1, specialty2));
        Profession profession2 = createProfession("Prof3", "1", Set.of(specialty1, specialty2, specialty3));
        Profession profession3 = createProfession("Prof4", "1", Set.of(specialty1));
        Profession profession4 = createProfession("Prof5", "1", Set.of(specialty3));
        Profession profession5 = createProfession("Prof6", "2", Set.of(specialty2, specialty3));
        Profession profession6 = createProfession("Prof7", "2", Set.of(specialty2, specialty3));
        Profession profession7 = createProfession("Prof8", "2", Set.of(specialty2, specialty3));

        saveObjects(section1, section2, tDirection1, tDirection2, specialty1, specialty2, specialty3,
                profession0, profession1, profession2, profession3,
                profession4, profession5, profession6, profession7);
    }

    private String getExpectedResponse() throws JsonProcessingException {
        return objectMapper.writeValueAsString(getProfessions());
    }

    private String getExpectedSecondPageResponse() throws JsonProcessingException {
        return objectMapper.writeValueAsString(getProfessions().subList(2, 4));
    }

    private List<ProfessionDto> getProfessions() {
        ProfessionDto profession1 = new ProfessionDto(1, "Prof1");
        ProfessionDto profession2 = new ProfessionDto(2, "Prof2");
        ProfessionDto profession3 = new ProfessionDto(3, "Prof3");
        ProfessionDto profession4 = new ProfessionDto(4, "Prof4");

        return List.of(profession1, profession2, profession3, profession4);
    }

}

package ru.hh.univitrina.dao;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.entity.Profession;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static ru.hh.univitrina.dao.CreateObjectsUtil.createProfession;

public class ProfessionDaoTest extends UnivitrinaTestBase {

  @Inject
  private ProfessionDao professionDao;

  @Transactional
  @Test
  public void getSearchSuggestionTest() {
    Profession profession0 = createProfession("PROFGF", "1");
    Profession profession1 = createProfession("pRoFg", "1");
    Profession profession2 = createProfession("profhg", "1");
    Profession profession3 = createProfession("p r o f", "1");
    Profession profession4 = createProfession(" prof ", "1");

    saveObjects(profession0, profession1, profession2, profession3, profession4);

    List<Profession> expectedProfessions = List.of(profession0, profession1, profession2);

    List<Profession> actualProfessions = getActualProfessionList("pr", 5);

    assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
  }

  @Test
  public void getSearchSuggestionLimitTest() {
    Profession profession0 = createProfession("prof", "1");
    Profession profession1 = createProfession("prof", "1");
    Profession profession2 = createProfession("prof", "1");
    Profession profession3 = createProfession("prof", "1");
    Profession profession4 = createProfession("prof", "1");

    saveObjects(profession0, profession1, profession2, profession3, profession4);

    List<Profession> expectedProfessions = List.of(profession0, profession1, profession2);

    List<Profession> actualProfessions = getActualProfessionList("pr", 3);

    assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
  }

  @NotNull
  private List<Profession> getActualProfessionList(String prefix, int limit) {
    return transactionalScope.read(() -> professionDao.getSearchSuggestion(prefix, limit));
  }
}

package ru.hh.univitrina.dao;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hh.univitrina.UnivitrinaTestBase;
import ru.hh.univitrina.entity.Profession;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ProfessionDaoTest extends UnivitrinaTestBase {

  @Inject
  private ProfessionDao professionDao;

  @Transactional
  @Test
  public void getSearchSuggestionTest() {
    Profession profession0 = createProfession("PROFGF");
    Profession profession1 = createProfession("pRoFg");
    Profession profession2 = createProfession("profhg");
    Profession profession3 = createProfession("p r o f");
    Profession profession4 = createProfession(" prof ");

    saveObjects(profession0, profession1, profession2, profession3, profession4);

    List<Profession> expectedProfessions = List.of(profession0, profession1, profession2);

    List<Profession> actualProfessions = getActualProfessionList("pr", 5);

    assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
  }

  @Test
  public void getSearchSuggestionLimitTest() {
    Profession profession0 = createProfession("prof");
    Profession profession1 = createProfession("prof");
    Profession profession2 = createProfession("prof");
    Profession profession3 = createProfession("prof");
    Profession profession4 = createProfession("prof");

    saveObjects(profession0, profession1, profession2, profession3, profession4);

    List<Profession> expectedProfessions = List.of(profession0, profession1, profession2);

    List<Profession> actualProfessions = getActualProfessionList("pr", 3);

    assertThat(expectedProfessions, containsInAnyOrder(actualProfessions.toArray()));
  }

  @NotNull
  private List<Profession> getActualProfessionList(String prefix, int limit) {
    return transactionalScope.read(() -> professionDao.getSearchSuggestion(prefix, limit));
  }

  @NotNull
  private Profession createProfession(String name) {
    Profession profession = new Profession();
    profession.setName(name);
    profession.setHhapiId("1");
    return profession;
  }
}

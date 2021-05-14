package ru.hh.univitrina.resource;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import ru.hh.univitrina.dto.ProfessionShortDto;
import ru.hh.univitrina.dto.SpecialtyShortDto;
import ru.hh.univitrina.dto.UniversityShortDto;
import ru.hh.univitrina.service.ProfessionService;
import ru.hh.univitrina.service.SpecialtyService;
import ru.hh.univitrina.service.UniversityService;

@Singleton
@Path("/search/suggest")
public class SuggestResource {
  private final UniversityService universityService;
  private final SpecialtyService specialtyService;
  private final ProfessionService professionService;

  @Inject
  public SuggestResource(UniversityService universityService,
                         SpecialtyService specialtyService,
                         ProfessionService professionService) {
    this.universityService = universityService;
    this.specialtyService = specialtyService;
    this.professionService = professionService;
  }

  @GET
  @Path("/university")
  @Produces(MediaType.APPLICATION_JSON)
  public List<UniversityShortDto> getUniversitySuggest(@QueryParam("prefix") String prefix,
                                                       @DefaultValue("10") @QueryParam("limit") Integer limit) {
    validate(prefix, limit);
    return universityService.getSearchSuggestion(prefix.trim(), limit);
  }

  @GET
  @Path("/specialty")
  @Produces(MediaType.APPLICATION_JSON)
  public List<SpecialtyShortDto> getSpecialtySuggest(@QueryParam("prefix") String prefix,
                                                     @DefaultValue("10") @QueryParam("limit") Integer limit) {
    validate(prefix, limit);
    return specialtyService.getSearchSuggestion(prefix.trim(), limit);
  }

  @GET
  @Path("/profession")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProfessionShortDto> getProfessionSuggest(@QueryParam("prefix") String prefix,
                                                       @DefaultValue("10") @QueryParam("limit") Integer limit) {
    validate(prefix, limit);
    return professionService.getSearchSuggestion(prefix.trim(), limit);
  }

  private void validate(String prefix, Integer limit) {
    if (prefix == null || prefix.trim().equals("")) {
      throw new IllegalArgumentException("Prefix must be not null and not empty");
    }
    if (limit < 0) {
      throw new IllegalArgumentException("Limit value must be 0 or greater");
    }
  }
}

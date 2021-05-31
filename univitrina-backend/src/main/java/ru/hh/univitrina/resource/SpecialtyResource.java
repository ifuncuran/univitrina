package ru.hh.univitrina.resource;

import ru.hh.univitrina.dto.SpecialtyDto;
import ru.hh.univitrina.service.SpecialtyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/specialties")
public class SpecialtyResource {

  private final SpecialtyService specialtyService;

  @Inject
  public SpecialtyResource(SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }

  @GET
  @Path("/professionId/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<SpecialtyDto> getFilteredByProfession(@PathParam("id") Integer id,
                                                  @QueryParam("page") @DefaultValue("1") Integer page,
                                                  @QueryParam("per_page") @DefaultValue("10") Integer perPage) {
    return specialtyService.getFilteredByProfession(page, perPage, id);
  }

}

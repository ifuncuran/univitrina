package ru.hh.univitrina.resource;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.hh.univitrina.dto.UniversityDto;
import ru.hh.univitrina.service.UniversityService;

@Singleton
@Path("/universities")
public class UniversityResource {

  private final UniversityService universityService;

  @Inject
  public UniversityResource(UniversityService universityService) {
    this.universityService = universityService;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public UniversityDto getUniversityById(@PathParam("id") Integer id) {
    return universityService.getById(id).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public List<UniversityDto> getUniversityList(@QueryParam("area") Integer areaId,
                                               @QueryParam("specialty") Integer specialtyId,
                                               @QueryParam("text") String text,
                                               @QueryParam("page") @DefaultValue("0") Integer page,
                                               @QueryParam("perPage") @DefaultValue("20") Integer perPage) {
    return universityService.getUniversityList(areaId, specialtyId, text, page, perPage);
  }
}

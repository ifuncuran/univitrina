package ru.hh.univitrina.resource;


import ru.hh.univitrina.dto.AreaDto;
import ru.hh.univitrina.service.AreaService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/areas")
public class AreaResource {
  private final AreaService areaService;

  @Inject
  public AreaResource(AreaService areaService) {
    this.areaService = areaService;
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AreaDto> getAllCities() {
    return areaService.getAllCities();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAreaNameById(@PathParam("id") Integer id) {
    return areaService.getAreaNameById(id);
  }

  @GET
  @Path("/areasWithUniversity")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AreaDto> getCitiesWithUniversity() {
    return areaService.getCitiesWithUniversity();
  }
}

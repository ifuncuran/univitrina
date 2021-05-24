package ru.hh.univitrina.resource;

import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.service.ProfessionService;

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
@Path("/professions")
public class ProfessionResource {

    private final ProfessionService professionService;

    @Inject
    public ProfessionResource(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GET
    @Path("/specialty/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfessionDto> getFilteredBySpecialty(@PathParam("id") Integer id,
                                                      @QueryParam("page") @DefaultValue("1") Integer page,
                                                      @QueryParam("per_page") @DefaultValue("10") Integer perPage) {
        return professionService.getFilteredBySpecialty(page, perPage, id);
    }

}

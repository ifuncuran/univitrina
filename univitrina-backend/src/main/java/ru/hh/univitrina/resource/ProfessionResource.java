package ru.hh.univitrina.resource;

import ru.hh.univitrina.dto.ProfessionDto;
import ru.hh.univitrina.dto.ProfessionShortDto;
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
    @Path("/specialty/{specialtyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfessionDto> getFilteredBySpecialty(@PathParam("specialtyId") Integer specialtyId,
                                                      @QueryParam("page") @DefaultValue("0") Integer page,
                                                      @QueryParam("per_page") @DefaultValue("32") Integer perPage) {
        return professionService.getFilteredBySpecialty(page, perPage, specialtyId);
    }

    @GET
    @Path("/{professionId}")
    @Produces(MediaType.APPLICATION_JSON) public ProfessionDto getProfessionById(
            @PathParam("professionId") Integer professionId) {

        return professionService.getProfessionById(professionId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfessionDto> getFilteredBySearchCriteria(@QueryParam("profession_name") String professionName,
                                                           @QueryParam("specialty_id") Integer specialtyId) {
        if (professionName != null && specialtyId != null) {
            return professionService.getFilteredByProfessionNameAndSpecialtyId(professionName, specialtyId);
        } else if (professionName != null) {
            return professionService.getFilteredByProfessionName(professionName);
        } else if (specialtyId != null) {
            return professionService.getFilteredBySpecialtyId(specialtyId);
        } else {
            return professionService.getAllProfessions();
        }
    }

    @GET
    @Path("/dictionary")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfessionShortDto> getDictionary() {
        return professionService.getDictionary();
    }
}

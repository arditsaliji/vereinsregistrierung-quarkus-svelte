package service;

import dataaccess.MitgliedDataAccess;
import model.Mitglied;
import model.Verein;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/mitglieder")
public class MitgliedRestService {

    @Inject
    MitgliedDataAccess mitgliedDataAccess;
    @Inject
    MitgliedService mitgliedService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mitglied> getAllMitglieder() throws Exception {
        return mitgliedDataAccess.getMitglieder();
    }

    @Path("filtered/{filterString}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mitglied> getFilteredMitglieder(@PathParam("filterString") String filterString) throws Exception {
        return mitgliedDataAccess.getFilteredMitglieder(filterString);
    }

    @Path("/delete/{mitgliedId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public int deleteVereinById(@PathParam("mitgliedId") int mitgliedId) throws Exception {
        return mitgliedService.deleteMitgliedById(mitgliedId);
    }

    @PUT
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int editMitgliedById(Mitglied mitglied) throws Exception {
        return mitgliedService.updateMitglied(mitglied);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int addVerein(Mitglied mitglied) {
        System.out.println(mitglied);
        return mitgliedService.addMitglied(mitglied);
    }
}

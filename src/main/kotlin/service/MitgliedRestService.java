package service;

import dataaccess.MitgliedDataAccess;
import model.Mitglied;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/mitglieder")
public class MitgliedRestService {

    @Inject
    MitgliedDataAccess mitgliedDataAccess;

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
}

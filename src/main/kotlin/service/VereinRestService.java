package service;

import dataaccess.VereinDataAccess;
import model.Verein;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vereine")
public class VereinRestService {

    @Inject
    VereinDataAccess vereinDataAccess;
    @Inject
    VereinService vereinService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Verein> getAllVereine() throws Exception {
        return vereinDataAccess.getVereine();
    }

    @Path("/delete/{vereinId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public int deleteVereinById(@PathParam("vereinId") int vereinId) throws Exception {
        return vereinService.deleteVereinById(vereinId);
    }

    @PUT
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int editVereinById(Verein verein) throws Exception {
        return vereinService.updateVerein(verein);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int addVerein(Verein verein) {
        System.out.println(verein);
        return vereinService.addVerein(verein);
    }


}

package service;

import dataaccess.VereinDataAccess;
import model.Verein;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vereine")
public class VereinRestService {

    @Inject
    VereinDataAccess vereinDataAccess;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Verein> getAllVereine() throws Exception {
        return vereinDataAccess.getVereine();
    }
}

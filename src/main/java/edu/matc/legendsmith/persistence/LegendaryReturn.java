package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/legendaries")
public class LegendaryReturn {

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/html"
    @Produces("text/html")
    public Response getMessage() {
        GenericDao dao = new GenericDao(Legendary.class);

        List<Legendary> legendaryList = dao.getAll();

        String html = "<table>";

        for (Legendary legendary : legendaryList
             ) {
            html += "<tr><td>" + legendary.getId() + "</td>";
            html += "<td>" + legendary.getName() + "</td>";
            html += "<td>" + legendary.getType() + "</td></tr>";
        }

        html += "</table>";

        return Response.status(200).entity(html).build();
    }

}
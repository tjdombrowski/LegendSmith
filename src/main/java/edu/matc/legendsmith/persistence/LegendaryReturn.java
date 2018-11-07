package edu.matc.legendsmith.persistence;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import edu.matc.legendsmith.entity.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/users")
public class LegendaryReturn {

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/html"
    @Produces("application/json")
    public Response getMessage() throws Exception {
        GenericDao dao = new GenericDao(User.class);

        List<User> objectList = dao.getAll();

        ObjectMapper mapper = new ObjectMapper();

        AnnotationIntrospector introspector
                = new JaxbAnnotationIntrospector();
        mapper.setAnnotationIntrospector(introspector);

        String result = mapper.writeValueAsString(objectList);

        /*String html = "<table>";

        for (Legendary legendary : legendaryList
             ) {
            html += "<tr><td>" + legendary.getId() + "</td>";
            html += "<td>" + legendary.getName() + "</td>";
            html += "<td>" + legendary.getType() + "</td></tr>";
        }

        html += "</table>";

        return Response.status(200).entity(html).build();*/

        return Response.status(200).entity(result).build();
    }

}
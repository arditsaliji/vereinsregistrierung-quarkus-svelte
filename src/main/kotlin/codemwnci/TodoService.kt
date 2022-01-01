package codemwnci

import io.vertx.core.json.JsonObject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/todos")
class TodoService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Ardit Saliji 25"

    // source https://codemwnci.medium.com/quarkus-web-application-with-sveltejs-ff03bae275a9
    // after frontend changes in component
        // 1. cd todo-frontend
        // 2. npm run build
        // 3. copy build folder in todo-frontent/public into src/main/resources/META-INF.resources
    // start kotlin app: mvn compile quarkus:dev
}

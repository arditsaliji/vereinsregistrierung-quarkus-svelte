package service

import bean.BucketBean
import model.Item
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/items")
class ItemService {

    @Inject
    private lateinit var bucket:  BucketBean

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getItems() = bucket.getItems()

    @POST
    @Path("/addItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addItem(item: Item): Item? {
        bucket.getItems().add(item)
        return item
    }

    @POST
    @Path("/editItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun editItem(item: Item): Item? {
        val itemToUpdate : Item = bucket.getItems().first { updItem -> updItem.getId() == item.getId() }
        itemToUpdate.setName(item.getName())
        itemToUpdate.setPrice(item.getPrice())
        return itemToUpdate
    }

    @POST
    @Path("/deleteItem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteItem(item: Item): ArrayList<Item> {
        val itemToDelete : Item = bucket.getItems().first { updItem -> updItem.getId() == item.getId() }
        bucket.getItems().remove(itemToDelete)
        return bucket.getItems()
    }



    // after frontend changes in component
        // 1. cd todo-frontend
        // 2. npm run build
        // 3. copy build folder in todo-frontent/public into src/main/resources/META-INF.resources
    // start kotlin app: mvn compile quarkus:dev
}

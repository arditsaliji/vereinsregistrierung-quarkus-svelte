package bean

import model.Item
import java.io.Serializable
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BucketBean() : Serializable{

    private val items = ArrayList<Item>();

    init {
        items.add(Item(1, "T-Shirt", 15))
        items.add(Item(2, "Hose", 75))
        items.add(Item(3, "Socken", 8))
        items.add(Item(4, "Schuhe", 110))
        items.add(Item(5, "Jacke", 120))
        items.add(Item(6, "Hemd", 40))
    }

    fun getItems(): ArrayList<Item> {
        return items
    }
}

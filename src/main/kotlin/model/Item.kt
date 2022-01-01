package model

class Item(private var id: Int, private var name: String, private var price: Int) {

    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getPrice(): Int {
        return price
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setPrice(price: Int) {
        this.price = price
    }
}

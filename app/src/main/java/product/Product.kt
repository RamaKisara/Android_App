package product

class Product {
    var id: Int? = null
    var merk: String? = null
    var tipe: String? = null
    var harga: String? = null

    constructor(id: Int, merk: String, tipe: String, harga:String){
        this.id = id
        this.merk = merk
        this.tipe = tipe
        this.harga = harga
    }
}
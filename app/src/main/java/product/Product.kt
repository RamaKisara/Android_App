package product
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Product (

        @PrimaryKey(autoGenerate = true)
        val id : Int = 0,
        val nama: String,
        val harga: String,


)
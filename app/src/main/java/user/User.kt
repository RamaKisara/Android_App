package user
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class User (

        @PrimaryKey(autoGenerate = true)
        val id : Int = 0,
        val nama: String,


)
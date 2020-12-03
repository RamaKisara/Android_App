package product
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
class ProductDB {
    @Database(
        entities = [Product::class],
        version = 1
    )
    abstract class ProductDB : RoomDatabase(){

        abstract fun productcrud() : ProductCrud

        companion object {

            @Volatile private var instance : ProductDB? = null
            private val LOCK = Any()

            operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

            private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                ProductDB::class.java,
                "product.db"
            ).build()

        }
    }

}
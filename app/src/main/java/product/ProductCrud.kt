package product
import androidx.room.*
@Dao
interface ProductCrud {
    @Insert
    suspend fun addUsr(product: Product)

    @Query ("SELECT * FROM product ORDER BY id DESC")
    suspend fun getprod() : List<Product>

    @Query("SELECT * FROM user WHERE id=:product_id")
    suspend fun getprod(product_id: Int) : List<Product>

    @Update
    suspend fun updateprod(product: Product)

    @Delete
    suspend fun deleteprod(product: Product)
}
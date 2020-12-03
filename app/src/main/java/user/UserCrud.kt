package user
import androidx.room.*
@Dao
interface UserCrud {
    @Insert
    suspend fun addUsr(user: User)

    @Query ("SELECT * FROM user ORDER BY id DESC")
    suspend fun getUsr() : List<User>

    @Query("SELECT * FROM user WHERE id=:user_id")
    suspend fun getUsr(user_id: Int) : List<User>

    @Update
    suspend fun updateUsr(user: User)

    @Delete
    suspend fun deleteUsr(user: User)
}
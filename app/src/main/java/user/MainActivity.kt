package user
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import user.MainUserActivity
import com.example.uas_mp.R
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_user.setOnClickListener {
            val hp = Intent(this, MainUserActivity::class.java)
            startActivity(hp)
        }

        btn_product.setOnClickListener {
            val laptop = Intent(this, MainProductActivity::class.java)
            startActivity(laptop)
        }
    }
}
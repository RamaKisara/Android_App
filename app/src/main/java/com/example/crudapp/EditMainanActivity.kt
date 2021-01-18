package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.crudapp.Database.AppRoomDB
import com.example.crudapp.Database.Constant
import com.example.crudapp.Database.Mainan
import com.example.crudapp.R
import kotlinx.android.synthetic.main.activity_edit_mainan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditMainanActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var mainanId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_mainan)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveMainan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.MainanDao().addMainan(
                    Mainan(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
        btn_updateMainan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.MainanDao().updateMainan(
                    Mainan(mainanId, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_updateMainan.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_saveMainan.visibility = View.GONE
                btn_updateMainan.visibility = View.GONE
                getMainan()
            }
            Constant.TYPE_UPDATE -> {
                btn_saveMainan.visibility = View.GONE
                getMainan()
            }
        }
    }

    fun getMainan() {
        mainanId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val mainans =  db.MainanDao().getMainan( mainanId )[0]
            txt_merk.setText( mainans.merk )
            txt_stok.setText( mainans.stok.toString() )
            txt_harga.setText( mainans.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
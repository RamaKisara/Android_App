package com.example.crudapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudapp.Database.AppRoomDB
import com.example.crudapp.Database.Constant
import com.example.crudapp.Database.Mainan
import com.example.crudapp.R
import kotlinx.android.synthetic.main.activity_mainan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainanActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var mainanAdapter: MainanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainan)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadMainan()
    }

    fun loadMainan(){
        CoroutineScope(Dispatchers.IO).launch {
            val allMainan = db.MainanDao().getAllMainan()
            Log.d("Mainanctivity", "dbResponse: $allMainan")
            withContext(Dispatchers.Main) {
                mainanAdapter.setData(allMainan)
            }
        }
    }

    fun setupListener() {
        btn_createMainan.setOnClickListener {
           intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        mainanAdapter = MainanAdapter(arrayListOf(), object: MainanAdapter.OnAdapterListener {
            override fun onClick(mainan: Mainan) {
                // read detail
                intentEdit(mainan.id, Constant.TYPE_READ)
            }

            override fun onDelete(mainan: Mainan) {
                // delete data
                deleteDialog(mainan)
            }

            override fun onUpdate(mainan: Mainan) {
                // edit data
                intentEdit(mainan.id, Constant.TYPE_UPDATE)
            }

        })
        list_mainan.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainanAdapter
        }
    }

    fun intentEdit(mainanId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditMainanActivity::class.java)
                .putExtra("intent_id", mainanId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(mainan: Mainan) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.MainanDao().deleteMainan(mainan)
                    loadMainan()
                }
            }
        }
        alertDialog.show()
    }
}
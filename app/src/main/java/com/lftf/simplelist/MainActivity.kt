package com.lftf.simplelist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lftf.simplelist.adapters.RVAdapter
import com.lftf.simplelist.addItem.addItemActivity
import com.lftf.simplelist.data.DataManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("O valor de savedInstanceState é: $savedInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle) {

        println("O valor de savedInstanceState é: $outState")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        println("O valor de savedInstanceState é: $savedInstanceState")

        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun displayContent() {
        val list = DataManager.getList()
        val adapter = RVAdapter(list)
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        findViewById<RecyclerView>(R.id.recycler_view_list).apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, addItemActivity::class.java )
            startActivity(intent)
        })
    }

    override fun onResume() {
        displayContent()
        super.onResume()
    }
}
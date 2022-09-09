package com.lftf.simplelist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
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

        val rv = findViewById<RecyclerView>(R.id.recycler_view_list)
        val list = DataManager.getExamples()
        val adapter = RVAdapter(list)
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        rv.layoutManager = layoutManager
        rv.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, addItemActivity::class.java )
            startActivity(intent)
        })

    }
}
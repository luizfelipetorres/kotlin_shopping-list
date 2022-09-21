package com.lftf.simplelist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.lftf.simplelist.adapters.RVAdapter
import com.lftf.simplelist.addItem.AddItemActivity
import com.lftf.simplelist.data.DataManager
import com.lftf.simplelist.databinding.ActivityMainBinding

fun AppCompatActivity.insertToolbar(
    toolbar: Toolbar,
    title: String,
    displayBackButton: Boolean = false
) {
    setSupportActionBar(toolbar)
    supportActionBar?.title = title
    if (displayBackButton) {
        supportActionBar?.setDisplayHomeAsUpEnabled(displayBackButton)

    }
}



class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertToolbar(binding.toolbar.root, "Lista de itens")
        createRecyclerView()

        binding.fab.setOnClickListener(this)
    }


    private fun createRecyclerView() {
        val list = DataManager.getList()
        val adapter = RVAdapter(this, list)
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerViewList.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }

    override fun onResume() {
        createRecyclerView()
        super.onResume()
    }

    /**
     * Como a classe MainActivity está implementando View.OnClickListener, deveoms sobreescrever
     * esse médoto. Com isso, podemos passar as ações de click todas por essa função
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val intent = Intent(this, AddItemActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
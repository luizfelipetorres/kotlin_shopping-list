package com.lftf.simplelist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.lftf.simplelist.adapters.RVAdapter
import com.lftf.simplelist.addItem.AddItemActivity
import com.lftf.simplelist.databinding.ActivityMainBinding
import com.lftf.simplelist.repository.ItemRepository

/**
 * Extension para criação de toolbar/actionbar
 */
fun AppCompatActivity.insertToolbar(
    toolbar: Toolbar,
    title: String?,
    displayBackButton: Boolean = false
) {
    setSupportActionBar(toolbar)
    supportActionBar?.title = title ?: getString(R.string.app_name)
    if (displayBackButton)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayBackButton)
}

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertToolbar(binding.toolbar.root, "Lista de itens")

        //TODO: Remover
//        val repo = ItemRepository(this)
//        repo.getItens().forEach { i -> repo.delete(i.id) }
//        DataManager(this).list.forEach{i -> repo.save(i)}

        createRecyclerView()

        //Log.d("RVA", "${ItemRepository(this).getItens()}")

        binding.fab.setOnClickListener(this)
    }


    private fun createRecyclerView() {
        val repo = ItemRepository(this)
        val list = repo.getItens()
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
                val intent = Intent(this, AddItemActivity::class.java).apply {
                    putExtra(AddItemActivity.TOOLBAR_TITLE, AddItemActivity.CRIAR_ITEM)
                }
                startActivity(intent)
            }
        }
    }
}
package com.tistory.black_jin0427.navermovie.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.black_jin0427.navermovie.BaseActivity
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.ApiProvider
import com.tistory.black_jin0427.navermovie.api.model.MovieItem
import com.tistory.black_jin0427.navermovie.utils.Dlog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), MainAdapter.OnItemClickListener {

    private val compositeDisposable = CompositeDisposable()

    private val mainAdapter by lazy {
        MainAdapter().apply { setClickListener(this@MainActivity) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideProgress()

        with(rvActivityMain){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        svActivityMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {

                text.let { query ->
                    if(query.isNullOrEmpty()) {
                        showEmptyData()
                    } else {
                        hideEmptyData()
                        loadData(query)
                    }

                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                //Dlog.d("onQueryTextChange text : $text")
                return false
            }

        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onClick(movieItem: MovieItem) {
        toast(movieItem.title)
    }

    private fun loadData(query: String) {

        ApiProvider.provideNaverApi().movie(query)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgress()
            }
            .doOnSuccess {
                hideProgress()
            }
            .subscribe({

                emptySearchView()
                mainAdapter.setItems(it.items)

            }){
                Dlog.e(it.message)
            }.also {
                compositeDisposable.add(it)
            }
    }

    private fun showProgress() {
        pbActivityMain.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pbActivityMain.visibility = View.GONE
    }

    private fun showEmptyData() {
        tvActivityMainEmptyData.visibility = View.VISIBLE
    }

    private fun hideEmptyData() {
        tvActivityMainEmptyData.visibility = View.GONE
    }

    private fun emptySearchView() {
        svActivityMain.setQuery("",false)
    }
}

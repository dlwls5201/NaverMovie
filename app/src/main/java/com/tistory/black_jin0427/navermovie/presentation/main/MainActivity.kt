package com.tistory.black_jin0427.navermovie.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.black_jin0427.navermovie.BaseActivity
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.constant.AppSchedulerProvider
import com.tistory.black_jin0427.navermovie.data.repository.BookRepositoryImpl
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepositoryImpl
import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSourceImpl
import com.tistory.black_jin0427.navermovie.data.source.remote.RemoteClient
import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import com.tistory.black_jin0427.navermovie.presentation.model.BookItem
import com.tistory.black_jin0427.navermovie.presentation.model.MovieItem
import com.tistory.black_jin0427.navermovie.presentation.model.mapToPresentation
import com.tistory.black_jin0427.navermovie.utils.Dlog
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var presenter: MainPresenter

    private val movieAdapter by lazy {
        MovieAdapter().apply {
            setClickListener(object :
                MovieAdapter.OnItemClickListener {
                override fun onClick(movieItem: MovieItem) {
                    toast(movieItem.title)
                }
            })
        }
    }

    private val bookAdapter by lazy {
        BookAdapter().apply {
            setClickListener(object :
                BookAdapter.OnItemClickListener {
                override fun onClick(bookItem: BookItem) {
                    toast(bookItem.title)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(
            this,
            GetContentsUsecase(
                BookRepositoryImpl.getInstance(
                    NaverRemoteDataSourceImpl(RemoteClient.naverService)
                ),
                MovieRepositoryImpl.getInstance(
                    NaverRemoteDataSourceImpl(RemoteClient.naverService)
                ),
                AppSchedulerProvider
            )
        )

        initView()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun initView() {
        initBookRecyclerView()
        initMovieRecyclerView()
        initSearchView()
        showEmptyText()
    }

    private fun initBookRecyclerView() {
        with(rvActivityMainMovie) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
    }

    private fun initMovieRecyclerView() {
        with(rvActivityMainBook) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.HORIZONTAL, false)
            adapter = bookAdapter
        }

    }

    private fun initSearchView() {
        svActivityMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {

                text.let { query ->
                    //Dlog.e("query : $query")
                    if (query.isNullOrEmpty()) {
                        showEmptyText()
                    } else {
                        presenter.loadData(query)
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

    override fun showProgress() {
        pbActivityMain.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbActivityMain.visibility = View.GONE
    }

    override fun showEmptyText() {
        tvActivityMainEmptyData.visibility = View.VISIBLE
    }

    override fun hideEmptyText() {
        tvActivityMainEmptyData.visibility = View.GONE
    }

    override fun showBookItems(items: MutableList<BookItem>) {
        bookAdapter.setItems(items)
    }

    override fun showMovieItems(items: MutableList<MovieItem>) {
        movieAdapter.setItems(items)
    }

    override fun showBookItemsCleared() {
        bookAdapter.clear()
    }

    override fun showMovieItemsCleared() {
       movieAdapter.clear()
    }

    override fun showSearchTextEmpty() {
        svActivityMain.setQuery("", false)
    }

    override fun showToast(msg: String) {
        toast(msg)
    }
}

package com.tistory.black_jin0427.navermovie.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.black_jin0427.navermovie.BaseActivity
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.data.repository.BookRepositoryImpl
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepositoryImpl
import com.tistory.black_jin0427.navermovie.data.response.BookResponse
import com.tistory.black_jin0427.navermovie.data.response.MovieResponse
import com.tistory.black_jin0427.navermovie.data.source.remote.RemoteClient
import com.tistory.black_jin0427.navermovie.constant.AppSchedulerProvider
import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import com.tistory.black_jin0427.navermovie.utils.Dlog
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private val compositeDisposable = CompositeDisposable()

    private val movieAdapter by lazy {
        MovieAdapter().apply {
            setClickListener(object : MovieAdapter.OnItemClickListener {
                override fun onClick(movieResponse: MovieResponse) {
                    toast(movieResponse.title)
                }
            })
        }
    }

    private val bookAdapter by lazy {
        BookAdapter().apply {
            setClickListener(object : BookAdapter.OnItemClickListener {
                override fun onClick(bookResponse: BookResponse) {
                    toast(bookResponse.title)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideProgress()

        with(rvActivityMainMovie) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        with(rvActivityMainBook) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.HORIZONTAL, false)
            adapter = bookAdapter
        }

        svActivityMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {

                text.let { query ->
                    if (query.isNullOrEmpty()) {
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

    private fun loadData(query: String) {

        GetContentsUsecase(
            BookRepositoryImpl(RemoteClient.naverMovieService),
            MovieRepositoryImpl(RemoteClient.naverMovieService),
            AppSchedulerProvider
        ).get(query).doOnSubscribe {
            showProgress()
        }.doOnSuccess {
            hideProgress()
        }.subscribe({
            emptySearchView()
            movieAdapter.setItems(it.movies)
            bookAdapter.setItems(it.books)
        }) {
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
        svActivityMain.setQuery("", false)
    }
}

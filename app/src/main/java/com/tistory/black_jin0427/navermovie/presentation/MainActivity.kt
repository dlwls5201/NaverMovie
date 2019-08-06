package com.tistory.black_jin0427.navermovie.presentation

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
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private val compositeDisposable = CompositeDisposable()

    private val movieAdapter by lazy {
        MovieAdapter().apply {
            setClickListener(object : MovieAdapter.OnItemClickListener {
                override fun onClick(movieItem: MovieItem) {
                    toast(movieItem.title)
                }
            })
        }
    }

    private val bookAdapter by lazy {
        BookAdapter().apply {
            setClickListener(object : BookAdapter.OnItemClickListener {
                override fun onClick(bookItem: BookItem) {
                    toast(bookItem.title)
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
                        showEmptyText()
                    } else {
                        hideEmptyText()
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
            BookRepositoryImpl.getInstance(
                NaverRemoteDataSourceImpl(RemoteClient.naverService)
            ),
            MovieRepositoryImpl.getInstance(
                NaverRemoteDataSourceImpl(RemoteClient.naverService)
            ),
            AppSchedulerProvider
        ).get(query).doOnSubscribe {
            showProgress()
        }.doOnSuccess {
            hideProgress()
        }.subscribe({
            emptySearchText()
            bookAdapter.setItems(it.first.mapToPresentation())
            movieAdapter.setItems(it.second.mapToPresentation())
        }) {
            Dlog.e(it.message)
            toast(it.message.toString())
            hideProgress()
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

    private fun showEmptyText() {
        tvActivityMainEmptyData.visibility = View.VISIBLE
    }

    private fun hideEmptyText() {
        tvActivityMainEmptyData.visibility = View.GONE
    }

    private fun emptySearchText() {
        svActivityMain.setQuery("", false)
    }
}

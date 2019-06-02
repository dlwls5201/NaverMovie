package com.tistory.black_jin0427.navermovie.view

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.tistory.black_jin0427.navermovie.BaseActivity
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.ApiProvider
import com.tistory.black_jin0427.navermovie.api.model.MovieItem
import com.tistory.black_jin0427.navermovie.databinding.ActivityMainBinding
import com.tistory.black_jin0427.navermovie.utils.Dlog
import com.tistory.black_jin0427.navermovie.viewModel.movieList.MovieListViewModel
import com.tistory.black_jin0427.navermovie.viewModel.movieList.MovieListViewModelFactory
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<ActivityMainBinding>(), MainAdapter.OnItemClickListener {

    override val layoutResourceId = R.layout.activity_main

    private val mainAdapter by lazy {
        MainAdapter().apply { setClickListener(this@MainActivity) } }

    private lateinit var movieListViewModel: MovieListViewModel

    private lateinit var movieListViewModelFactory: MovieListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 모델 팩토리 선언
        movieListViewModelFactory = MovieListViewModelFactory(
            mainAdapter,
            ApiProvider.provideNaverApi()
        )

        // 뷰 모델 초기화 및 관찰자 연결
        movieListViewModel = ViewModelProviders.of(this, movieListViewModelFactory)
            .get(MovieListViewModel::class.java)

        // 데이터 바인딩에 뷰 모델 연결
        viewDataBinding.model = movieListViewModel

        // 데이터 바인딩에 LifecycleOwner 연결하여 liveData 를 DataBinding 과 사용 할 수 있게 함
        viewDataBinding.lifecycleOwner = this

        // search view 동작
        viewDataBinding.svActivityMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                text?.run {
                    movieListViewModel.loadData(this)
                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                //Dlog.d("onQueryTextChange text : $text")
                return false
            }

        })
    }

    override fun onClick(movieItem: MovieItem) {
        toast(movieItem.title)
    }
}

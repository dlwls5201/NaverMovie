package com.tistory.black_jin0427.navermovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tistory.black_jin0427.navermovie.api.ApiProvider
import com.tistory.black_jin0427.navermovie.utils.Dlog
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiProvider.provideNaverApi()
            .movie("마블")
            .subscribe({
                Dlog.d("$it")
            }){
                Dlog.e(it.message)
            }
            .also {
                compositeDisposable.add(it)
            }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}

/*package com.duzgundogan.udemykotlin.view

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.duzgundogan.udemykotlin.R
import com.duzgundogan.udemykotlin.adapter.recyclerViewAdapter
import com.duzgundogan.udemykotlin.databinding.ActivityMainBinding
import com.duzgundogan.udemykotlin.model.CryptoModel
import com.duzgundogan.udemykotlin.model.CryptoResponse
import com.duzgundogan.udemykotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity(), recyclerViewAdapter.Listener {

    private val BASE_URL = "https://api.coincap.io/v2/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private lateinit var cryptoAdapter: recyclerViewAdapter
    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView Ayarı
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        // Verileri yükle
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        compositeDisposable.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(cryptoResponse: CryptoResponse) {
        val cryptoList = cryptoResponse.data ?: arrayListOf()
        cryptoAdapter = recyclerViewAdapter(ArrayList(cryptoList), this)
        binding.recyclerView.adapter = cryptoAdapter
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked: ${cryptoModel.symbol}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear() // Bellek sızıntısını önlemek için clear() çağrılır
    }
}
*/
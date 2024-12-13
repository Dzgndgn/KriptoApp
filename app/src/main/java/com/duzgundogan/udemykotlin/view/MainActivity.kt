package com.duzgundogan.udemykotlin.view


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duzgundogan.udemykotlin.NewActivity
//import com.duzgundogan.udemykotlin.adapter.RecyclerViewAdapter
import com.duzgundogan.udemykotlin.databinding.ActivityMainBinding
import com.duzgundogan.udemykotlin.adapter.recyclerViewAdapter
import com.duzgundogan.udemykotlin.model.CryptoModel
import com.duzgundogan.udemykotlin.model.CryptoResponse
import com.duzgundogan.udemykotlin.service.CryptoAPI
import com.duzgundogan.udemykotlin.ui.theme.UdemyKotlinTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() ,recyclerViewAdapter.Listener{
    val apikey="dd8e1983-1822-49fe-815c-ed8069e7a6d8"
    private val BASE_URL ="https://pro-api.coinmarketcap.com"
    private var cryptoModels : ArrayList<CryptoModel>? =null
    private lateinit var recyclerViewAdapter: recyclerViewAdapter
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        enableEdgeToEdge()

         //val layoutManager :RecyclerView.LayoutManager = LinearLayoutManager(this)
        //findViewById<RecyclerView>(R.id.recyclerView).layoutManager =layoutManager
      // binding.recyclerView.layoutManager =LinearLayoutManager(this)
        val layoutManager : RecyclerView.LayoutManager =LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        //val MyRecycleView =RecyclerViewAdapter(Cry)
        loadData()

    }
    private fun loadData(){
        val retrofit =Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service =retrofit.create(CryptoAPI::class.java)
        val call =service.getData(apikey)
        call.enqueue(object : Callback<CryptoResponse> {
            override fun onResponse(call: Call<CryptoResponse>, response: Response<CryptoResponse>) {
                if (response.isSuccessful) {
                    // Başarılı yanıt işleme
                    val cryptoList = response.body()?.data ?: arrayListOf()
                    println("Crypto list size: ${cryptoList.size}")
                    recyclerViewAdapter = recyclerViewAdapter(ArrayList(cryptoList), this@MainActivity)
                    binding.recyclerView.adapter = recyclerViewAdapter

                } else {
                    // Başarısız yanıt işleme
                    println("Response failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CryptoResponse>, t: Throwable) {
                // Hata işleme
                println("Request failed: ${t.message}")
            }
        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        val intent = Intent(this,NewActivity::class.java)
        startActivity(intent)
        println("denemedeneme")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UdemyKotlinTheme {
        Greeting("Android")
    }

}

package com.duzgundogan.udemykotlin.service

import android.telecom.Call
import com.duzgundogan.udemykotlin.model.CryptoModel
import com.duzgundogan.udemykotlin.model.CryptoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/*interface CryptoAPI {
    @GET("assets")
    fun getData():retrofit2.Call<List<CryptoModel>>
}*/
// 5577fe13-886b-4a92-a01d-cc305d0d00ff

interface CryptoAPI {
    @GET("v1/cryptocurrency/listings/latest")
    fun getData(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("start") start: Int = 1, // Başlangıç
        @Query("limit") limit: Int = 50 // En fazla 50 sonuç
    ): retrofit2.Call<CryptoResponse>


}

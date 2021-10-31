package fahimeh.eltejaei.myweatherapp.network

import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import fahimeh.eltejaei.myweatherapp.network.datamodel.weatherResponse
import retrofit2.http.*

interface ApiService {

    @GET("2.5/onecall")
    suspend fun getWeather(@Query("lat") lat:Float, @Query("lon") lon:Float, @Query("appid") appid:String): weatherResponse

}
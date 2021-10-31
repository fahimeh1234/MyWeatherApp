package fahimeh.eltejaei.myweatherapp.network

import fahimeh.eltejaei.mydigikala.myweatherapp.errorHandling.Resource
import fahimeh.eltejaei.myweatherapp.network.datamodel.weatherResponse

interface ApiRepository {

    suspend fun getWeather(lat:Float,lon:Float,appid:String): Resource<weatherResponse>

}
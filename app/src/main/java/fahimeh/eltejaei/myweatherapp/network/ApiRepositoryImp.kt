package fahimeh.eltejaei.myweatherapp.network

import fahimeh.eltejaei.mydigikala.myweatherapp.errorHandling.Resource
import fahimeh.eltejaei.myweatherapp.errorHandling.ResponseHandler
import fahimeh.eltejaei.myweatherapp.network.datamodel.weatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ApiRepositoryImp @Inject constructor(private val apiService: ApiService,private val responseHandler: ResponseHandler) : ApiRepository {

    override suspend fun getWeather(
        lat: Float,
        lon: Float,
        appid: String
    ): Resource<weatherResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = apiService.getWeather(lat,lon,appid)
                responseHandler.handleSuccess(result)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
}
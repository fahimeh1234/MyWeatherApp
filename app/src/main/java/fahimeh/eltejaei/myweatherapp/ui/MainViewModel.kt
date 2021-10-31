package fahimeh.eltejaei.myweatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fahimeh.eltejaei.mydigikala.myweatherapp.errorHandling.Resource
import fahimeh.eltejaei.myweatherapp.network.ApiRepository
import fahimeh.eltejaei.myweatherapp.network.datamodel.weatherResponse
import fahimeh.eltejaei.myweatherapp.utils.APP_ID
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel(){

    private val _weatherResponse = MutableLiveData<Resource<weatherResponse>>()
    val weatherResponse: LiveData<Resource<weatherResponse>>
        get() = _weatherResponse

    fun getWeather(lat: Float,lon: Float){
        viewModelScope.launch {
            _weatherResponse.value = apiRepository.getWeather(lat,lon, APP_ID)
        }

    }
}
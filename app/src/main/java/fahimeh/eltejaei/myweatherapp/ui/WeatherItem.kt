package fahimeh.eltejaei.myweatherapp.ui

import com.xwray.groupie.databinding.BindableItem
import fahimeh.eltejaei.myweatherapp.R

import fahimeh.eltejaei.myweatherapp.databinding.ItemWeatherBinding
import fahimeh.eltejaei.myweatherapp.network.datamodel.Daily
import java.text.SimpleDateFormat
import java.util.*


class WeatherItem (val daily: Daily): BindableItem<ItemWeatherBinding>(){
    override fun bind(dataBinding: ItemWeatherBinding, position: Int) {

        dataBinding.daily = daily
        dataBinding.txtDate.text = getDateTime((daily.dt?.toLong()?.times(1000)) ?:0)
        val tempText = convertKelvinToCelcius(((daily.temp?.day ?:0) as Double)).toString()
        dataBinding.txtTemp.text = tempText.substring(0,tempText.indexOf('.'))+"°C"
    }

    override fun getLayout()= R.layout.item_weather

    private fun convertKelvinToCelcius(kelvin: Double): Double {
        return (kelvin - 273.15F)
    }

    private fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}
package fahimeh.eltejaei.myweatherapp.network.datamodel


import com.squareup.moshi.Json

data class Minutely(
    @Json(name = "dt")
    val dt: Int?,
    @Json(name = "precipitation")
    val precipitation: Int?
)
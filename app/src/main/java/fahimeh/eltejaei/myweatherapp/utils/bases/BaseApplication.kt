package fahimeh.eltejaei.myweatherapp.utils.bases

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import fahimeh.eltejaei.myweatherapp.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class BaseApplication :Application() {

    override fun onCreate() {
        super.onCreate()
    }


}
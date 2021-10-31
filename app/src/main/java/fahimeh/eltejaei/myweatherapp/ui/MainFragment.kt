package fahimeh.eltejaei.myweatherapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.databinding.BindableItem
import dagger.hilt.android.AndroidEntryPoint
import fahimeh.eltejaei.myweatherapp.R
import fahimeh.eltejaei.myweatherapp.databinding.FragmentMainBinding
import fahimeh.eltejaei.myweatherapp.databinding.ItemWeatherBinding
import fahimeh.eltejaei.myweatherapp.network.datamodel.Daily
import fahimeh.eltejaei.myweatherapp.network.datamodel.weatherResponse
import okhttp3.internal.notifyAll

const val REQUEST_LOCATION_PERMISSION = 1000
@AndroidEntryPoint
class MainFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: FragmentMainBinding
    lateinit var map: GoogleMap



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.progressBar.visibility = View.VISIBLE
        viewModel.weatherResponse.observe(viewLifecycleOwner){
            it.data?.daily?.let { it1 -> createRecyclerView(it1) }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            this.map = map
            enableMyLocation()
        }
//        map?.setOnMapLongClickListener {
//            viewModel.getWeather(it.latitude.toFloat(),it.longitude.toFloat())
//        }
        map?.setOnCameraIdleListener {
            val centerLocation = map?.cameraPosition?.target
            centerLocation?.let {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getWeather(centerLocation.latitude.toFloat(),centerLocation.longitude.toFloat())
            }


        }
    }

    private fun createRecyclerView(dailylist: List<Daily>) {
        val adapter = GroupieAdapter()
        val DailyListItem = mutableListOf<BindableItem<*>>()
        dailylist.forEachIndexed { index, Daily ->

            DailyListItem.add(WeatherItem(Daily))
        }
        adapter.addAll(DailyListItem)
        binding.rvWeathers.adapter = adapter
        binding.progressBar.visibility = View.INVISIBLE
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }


    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}
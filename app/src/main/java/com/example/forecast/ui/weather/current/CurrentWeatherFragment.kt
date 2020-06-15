package com.example.forecast.ui.weather.current

import ApixuWeatherApiService
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ActionMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.forecast.R
import com.example.forecast.data.network.response.ConnectivityInterceptorImpl
import com.example.forecast.data.network.response.WeatherNetworkDataSource
import com.example.forecast.data.network.response.WeatherNetworkDataSourceImpl
import com.example.forecast.databinding.CurrentWeatherFragmentBinding
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.security.auth.callback.Callback

class CurrentWeatherFragment : Fragment() {
private lateinit var binding:CurrentWeatherFragmentBinding
    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.current_weather_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)

        var apiService= ApixuWeatherApiService(ConnectivityInterceptorImpl(requireContext()))
        var weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            binding.text1.text=it.toString()

        })
        GlobalScope.launch(Dispatchers.Main){
            weatherNetworkDataSource.fetchCurrentWeather("Bhilai")
        }

        return binding.root

    }
}
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
import com.example.forecast.R
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
        val apiService= ApixuWeatherApiService()
        GlobalScope.launch(Dispatchers.Main){
            val currentWeatherResponse = apiService.getCurrentWeather("London").await()
            binding.text1.text=currentWeatherResponse.currentWeatherEntry.toString()
        }

        return binding.root

    }
}
package com.example.myweather.main_page.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.myweather.R
import com.example.myweather.databinding.FragmentWeatherBinding
import com.example.myweather.main_page.api.WeatherApi
import com.example.myweather.main_page.interactor.WeatherInteractor
import com.example.myweather.main_page.model.WeatherData
import com.example.myweather.main_page.repository.WeatherRemoteRepository
import com.example.myweather.utils.RetrofitClient
import kotlin.math.roundToInt

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.fragment_weather), WeatherContract.View {

    private val key: String by lazy {
        getString(R.string.key)
    }

    private val api: WeatherApi =
        RetrofitClient.getClient("https://api.openweathermap.org").create(WeatherApi::class.java)

    private val remoteRepository: WeatherRemoteRepository = WeatherRemoteRepository(api)

    private val interactor: WeatherInteractor = WeatherInteractor(remoteRepository)

    override val presenter: WeatherContract.Presenter = WeatherPresenter(interactor)

    private val kelvinConverter = KelvinConverter()


    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            editCity.doAfterTextChanged {
                if (it != null) {
                    if (it.isNotEmpty()) {
                        it.toString()
                            .let { city ->
                                if (city != "") presenter.getDataFromApi(city, key)
//                                showInfo()
                            }
                    }
                    if (it.isEmpty()) {
                        hideInfo()
                        deleteInfo()
                    }
                }
            }
        }
    }
    override fun showWeatherData(data: WeatherData) {
        with(binding) {
//                           degrees.text = "${data?.main?.temp.toString()} °C" "${data?.wind?.speed.toString()}  м/с"
            cityName.text = data.name
            degrees.text = kelvinConverter.convert(data.main.temp.roundToInt())
            binding.speedOfWindText.text = "${data.wind.speed}  м/с"
            binding.cloudiness.text = "${data.clouds.all}  %"
//            pressureText.text = "${data.main.pressure} гПа"

            visibilityText.text = kelvinConverter.metersiNKilometrs(data.visibility)
            description.text = if (data.weather.isNotEmpty())
                data.weather.first().description
            else print("sorry").toString()
        }
    }

    override fun showFailure(t: Throwable) {
        Log.d("####", "dataFailure: $t")
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    override fun showErrorMessage(t: Throwable?) {
        Toast.makeText(requireContext(), t?.message, Toast.LENGTH_SHORT).show()
        Log.d("1234", "showErrorMessage:${t?.message} ")
    }

//    override fun showErrorMessage(messageRes: String) {
//        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
//        Log.d("1234", "showErrorMessage:${messageRes} ")
//    }
//            binding.progress.isVisible = false

    fun hideInfo() {
        with(binding) {
            progress.isVisible = false
            cityName.isVisible = false
            degrees.isVisible = false
            description.isVisible = false
            speedOfWind.isVisible = false
            cloudCover.isVisible = false
//            pressure.isVisible = false // сделать давление
            visibility.isVisible = false
        }
    }

    fun deleteInfo() {
        with(binding) {
            cityName.text = ""
            degrees.text = ""
            description.text = ""
            cloudiness.text = ""
            visibilityText.text = ""
            cloudiness.text = ""
            speedOfWindText.text = ""
//            pressure.isVisible = false // сделать давление
        }
    }

    override fun showInfo() {
        with(binding) {
            cityName.isVisible = true
            degrees.isVisible = true
            description.isVisible = true
            speedOfWind.isVisible = true
            cloudiness.isVisible = true
            cloudCover.isVisible = true
//            pressure.isVisible = false // сделать давление
            visibility.isVisible = true
        }
    }
}
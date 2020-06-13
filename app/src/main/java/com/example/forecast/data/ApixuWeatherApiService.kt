import com.example.forecast.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//http://api.weatherapi.com/v1/current.json?key=18b8120a714e4b7dbc6185804201306&q=London
const val API_KEY = "18b8120a714e4b7dbc6185804201306"
private const val BASE_URL = "http://api.weatherstack.com/"


interface ApixuWeatherApiService{

    @GET("current.json")
    fun getCurrentWeather(@Query("q") location: String): Deferred<CurrentWeatherResponse>
    companion object {
        operator fun invoke(): ApixuWeatherApiService {
            val requestInterceptor=Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                  return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherapi.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}

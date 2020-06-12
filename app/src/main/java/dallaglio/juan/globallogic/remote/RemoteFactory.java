package dallaglio.juan.globallogic.remote;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteFactory {

    private static final String END_POINT = "https://private-f0eea-mobilegllatam.apiary-mock.com";
    private static final HttpLoggingInterceptor.Level LOGIN_LEVEL = HttpLoggingInterceptor.Level.BODY;

    <T> T createApiClient(Class<T> clazz){
        return createApiClient(clazz, END_POINT);
    }

    private <T> T createApiClient(Class<T> clazz, final String endPoint){
        Retrofit rest = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();
        return  rest.create(clazz);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLogginInterceptor())
                .build();
    }

    private Interceptor getLogginInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("Remote: ", message);
            }
        });
        loggingInterceptor.setLevel(LOGIN_LEVEL);
        return loggingInterceptor;
    }

}

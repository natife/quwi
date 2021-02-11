package com.natife.testapp.di.app;

import com.google.gson.Gson;
import com.natife.testapp.Constants;
import com.natife.testapp.api.ApiService;
import com.natife.testapp.api.AuthInterceptor;
import com.natife.testapp.prefs.PrefsRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public ApiService provideApiService(AuthInterceptor authInterceptor) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(
                        new OkHttpClient.Builder()
                                .addInterceptor(authInterceptor)
                                .build()
                )
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    public AuthInterceptor authInterceptor(PrefsRepository repository) {
        return new AuthInterceptor(repository);
    }
}

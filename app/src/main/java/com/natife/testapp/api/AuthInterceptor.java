package com.natife.testapp.api;

import androidx.annotation.NonNull;
import com.natife.testapp.prefs.PrefsRepository;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final PrefsRepository prefsRepository;

    public AuthInterceptor(PrefsRepository prefsRepository) {
        this.prefsRepository = prefsRepository;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + prefsRepository.getToken().blockingGet().orElse(""))
                .build();
        return chain.proceed(request);
    }
}

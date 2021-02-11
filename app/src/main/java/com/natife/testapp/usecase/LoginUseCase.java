package com.natife.testapp.usecase;

import com.natife.testapp.api.ApiService;
import com.natife.testapp.model.RequestLogin;
import com.natife.testapp.prefs.PrefsRepository;
import io.reactivex.Completable;
import javax.inject.Inject;

public class LoginUseCase {

    private final ApiService apiService;
    private final PrefsRepository prefsRepository;

    @Inject
    public LoginUseCase(ApiService apiService, PrefsRepository prefsRepository) {
        this.apiService = apiService;
        this.prefsRepository = prefsRepository;
    }

    public Completable execute(String email, String password) {
        return apiService.login(new RequestLogin(email, password))
                .doOnSuccess(responseLogin -> {
                    prefsRepository.saveToken(responseLogin.token);
                })
                .ignoreElement();
    }
}

package com.natife.testapp.ui.login;

import com.natife.testapp.NavMainDirections;
import com.natife.testapp.Router;
import com.natife.testapp.base.BaseViewModel;
import com.natife.testapp.usecase.LoginUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    private final LoginUseCase loginUseCase;
    private final Router router;

    @Inject
    public LoginViewModel(
            LoginUseCase loginUseCase,
            Router router
    ) {
        this.loginUseCase = loginUseCase;
        this.router = router;
    }

    public void login(String email, String password) {
        compositeDisposable.add(
                loginUseCase.execute(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> setLoading(true))
                        .doFinally(() -> setLoading(false))
                        .subscribe(
                                () -> router.navigate(NavMainDirections.actionGlobalNavMain()),
                                this::handleError
                        )
        );
    }
}

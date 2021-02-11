package com.natife.testapp.ui.start;

import com.natife.testapp.NavGlobalDirections;
import com.natife.testapp.Router;
import com.natife.testapp.base.BaseViewModel;
import com.natife.testapp.prefs.PrefsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class StartViewModel extends BaseViewModel {

    @Inject
    public StartViewModel(Router router, PrefsRepository prefsRepository) {
        compositeDisposable.add(
                prefsRepository.getToken()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(token -> {
                            if (token.isPresent()) {
                                router.navigate(NavGlobalDirections.actionGlobalNavMain());
                            } else {
                                router.navigate(NavGlobalDirections.actionGlobalNavAuth());
                            }
                        })
        );
    }
}

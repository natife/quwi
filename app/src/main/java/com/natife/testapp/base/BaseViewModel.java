package com.natife.testapp.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Throwable> _errorLiveData = new MutableLiveData<>();
    final LiveData<Throwable> errorLiveData = _errorLiveData;

    private final MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public final LiveData<Boolean> loadingLiveData = _loadingLiveData;

    protected void handleError(Throwable throwable) {
        _errorLiveData.setValue(throwable);
    }

    protected void setLoading(boolean isLoading) {
        _loadingLiveData.setValue(isLoading);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

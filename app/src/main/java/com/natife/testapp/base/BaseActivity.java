package com.natife.testapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.natife.testapp.App;
import com.natife.testapp.Router;
import com.natife.testapp.di.activity.ActivityComponent;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    public ActivityComponent component;

    @Inject
    public Router router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        component = ((App) getApplication()).appComponent
                .activityComponent()
                .create(this);
        injectWith(component);
        router.attach(this, getNavHostId());
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(this).inflate(getLayoutRes(), null, false);
        setContentView(contentView);
    }

    @Override
    protected void onDestroy() {
        router.detach();
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @IdRes
    protected abstract int getNavHostId();

    protected abstract void injectWith(ActivityComponent component);
}

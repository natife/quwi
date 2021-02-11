package com.natife.testapp;

import com.natife.testapp.base.BaseActivity;
import com.natife.testapp.di.activity.ActivityComponent;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getNavHostId() {
        return R.id.globalNavFragment;
    }

    @Override
    protected void injectWith(ActivityComponent component) {
        component.inject(this);
    }
}

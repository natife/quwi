package com.natife.testapp;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.IdRes;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Router {

    @Inject
    public Router() {

    }

    private Activity activity;

    @IdRes
    private Integer navHostId;

    public void attach(Activity activity, @IdRes int navHostId) {
        this.activity = activity;
        this.navHostId = navHostId;
    }

    public void navigateUp() {
        try {
            NavController navController = Navigation.findNavController(activity, navHostId);
            navController.navigateUp();
        } catch (Exception exc) {
            Log.e(Router.class.getSimpleName(), "Navigation error", exc);
        }
    }

    public void navigate(NavDirections navDirections) {
        try {
            NavController navController = Navigation.findNavController(activity, navHostId);
            navController.navigate(navDirections);
        } catch (Exception exc) {
            Log.e(Router.class.getSimpleName(), "Navigation error", exc);
        }
    }

    public void detach() {
        activity = null;
        navHostId = null;
    }
}

package com.natife.testapp.di.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.natife.testapp.App;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context context(App app) {
        return app;
    }

    @Provides
    @Singleton
    public SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences("main", Context.MODE_PRIVATE);
    }
}

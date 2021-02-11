package com.natife.testapp.prefs;

import android.content.SharedPreferences;
import io.reactivex.Single;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public
class PrefsRepository {

    private static final String KEY_TOKEN = "KEY_TOKEN";

    private final SharedPreferences prefs;

    @Inject
    public PrefsRepository(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public Single<Optional<String>> getToken() {
        return Single.fromCallable(() -> Optional.ofNullable(prefs.getString(KEY_TOKEN, null)));
    }
}

package pw.komarov.giphy;

import android.app.Application;
import android.content.Context;

final class GiphyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        GiphyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return GiphyApplication.context;
    }
}

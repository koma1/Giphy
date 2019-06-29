package pw.komarov.giphy;

import android.app.Application;
import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class GiphyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static byte[] getProcessingAnimationBytes() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.processing);
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

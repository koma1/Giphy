package pw.komarov.giphy;

import android.content.res.Resources;
import android.view.View;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class GifImageService {
    private static byte[] processingImageBytes;

    public static void loadProcessingAnimation(GifImageView gifImageView) {
        if (processingImageBytes == null) { //ToDo: Make it static by app ctx
            Resources res = gifImageView.getContext().getResources();
            InputStream inputStream = res.openRawResource(R.raw.processing);
            try {
                processingImageBytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        gifImageView.setBytes(processingImageBytes);
        gifImageView.setVisibility(View.VISIBLE);
        gifImageView.startAnimation();
    }

    public static void unloadProcessingAnimation(GifImageView gifImageView) {
        gifImageView.stopAnimation();
        gifImageView.setVisibility(View.INVISIBLE);
    }
}

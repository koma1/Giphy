package pw.komarov.giphy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import java.util.List;

public class GiphyService {
    private static final String GIPHY_API_KEY = "4StA7nAEVDwNlFFbPeIMvIR1kJ83FTTP";

    static final GPHApi client = new GPHApiClient(GIPHY_API_KEY);

    public static String getUrlById(String giphyID) {
        return new StringBuilder()
                .append(MEDIA_URL)
                .append(giphyID)
                .append("/200.gif").toString();
    }

    private final static String MEDIA_URL = "https://media.giphy.com/media/";
}

package pw.komarov.giphy.utils;

import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;

public class GiphyService {
    private static final String GIPHY_API_KEY = "4StA7nAEVDwNlFFbPeIMvIR1kJ83FTTP";
    static final GPHApi client = new GPHApiClient(GIPHY_API_KEY);

    private final static String MEDIA_URL_FMT = "https://media.giphy.com/media/%s/200.gif";

    public static String getUrlById(String giphyID) {
        return
                String.format(MEDIA_URL_FMT, giphyID);

        /*return new StringBuilder()
                .append(MEDIA_URL)
                .append(giphyID)
                .append("/200.gif").toString();*/
    }

    public static GPHApi getClient() {
        return client;
    }
}

package pw.komarov.giphy;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import pw.komarov.giphy.utils.GifImageService;
import pw.komarov.giphy.utils.GiphyService;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private TextView tvSearchHint;
    private GifImageView imgSearchProcessing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvSearchHint = findViewById(R.id.tvSearchHint);
        imgSearchProcessing = findViewById(R.id.imgSearchProcessing);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (s.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            tvSearchHint.setVisibility(View.VISIBLE);
        } else {
            tvSearchHint.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            GifImageService.loadProcessingAnimation(imgSearchProcessing);
            searchGiphy(s);
        }

        return true;
    }

    public void searchGiphy(@NonNull String searchText) {
        final String TAG = "searchGiphy()";

        try {
            Log.v(TAG, searchText);
            GiphyService.getClient().search(searchText, MediaType.gif, null, null, null,
                    null, null, new CompletionHandler<ListMediaResponse>() {
                        @Override
                        public void onComplete(ListMediaResponse result, Throwable e) {
                            GifImageService.unloadProcessingAnimation(imgSearchProcessing);
                            if (result != null) {
                                RVAdapter adapter = new RVAdapter(result.getData());
                                recyclerView.setAdapter(adapter); //ToDo: may be it need call in UI thread ctx?
                            } else {
                                Log.e(TAG + "[e.1]", e.getStackTrace().toString());
                            }
                        }
                    });
        } catch (Exception e) {
            GifImageService.unloadProcessingAnimation(imgSearchProcessing);
            Log.e(TAG + "[e.2]", e.getStackTrace().toString());
        }
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}

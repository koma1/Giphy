package pw.komarov.giphy;

import android.content.Context;
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
import android.widget.Toast;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListMediaResponse;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private TextView tvSearchHint;

    private static final String GIPHY_API_KEY = "4StA7nAEVDwNlFFbPeIMvIR1kJ83FTTP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvSearchHint = findViewById(R.id.tvSearchHint);

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
    public boolean onQueryTextChange(String s) {
        System.out.printf("onQueryTextChange('%s');\n", s);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (s == "") {
            recyclerView.setVisibility(View.INVISIBLE);
            tvSearchHint.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvSearchHint.setVisibility(View.INVISIBLE);

            searchGiphy(s);
        }

        return true;
    }

    private static final GPHApi client = new GPHApiClient(GIPHY_API_KEY);

    private void searchGiphy(@NonNull String searchText) {
        final Context ctx = this;
        //ToDo: move to svc layer
        Log.v("Search by: ", searchText);
        client.search(searchText, MediaType.gif, null, null, null,
                null, null, new CompletionHandler<ListMediaResponse>() {
                    @Override
                    public void onComplete(ListMediaResponse result, Throwable e) {
                        if (result != null) {
                            RVAdapter adapter = new RVAdapter(result.getData());
                            recyclerView.setAdapter(adapter);
                        } else
                            Toast.makeText(ctx, R.string.app_name, Toast.LENGTH_SHORT);
                    }
                });
    }
}

package pw.komarov.giphy;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeAdapter() {
        GiphyAdapter adapter = new GiphyAdapter(giphyItemList);
        recyclerView.setAdapter(adapter);
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
        return false;
    }

    private List<GiphyItem> giphyItemList;

    private void initializeData() {
        giphyItemList = new ArrayList<>();
        giphyItemList.add(new GiphyItem("dogs laughing GIF by The BarkPost", "cLcxtL1z8t8oo"));
        giphyItemList.add(new GiphyItem("dogs jumprope GIF", "fItgT774J3nWw"));
        giphyItemList.add(new GiphyItem("star wars dogs GIF", "mokQK7oyiR8Sk"));
    }
}

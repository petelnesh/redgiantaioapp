package com.keredgiantaio.techsavanna.redgiantaio.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.keredgiantaio.techsavanna.redgiantaio.R;
import com.keredgiantaio.techsavanna.redgiantaio.adapters.SweepSearchAdapter;
import com.keredgiantaio.techsavanna.redgiantaio.helpers.ApiClientSweep;
import com.keredgiantaio.techsavanna.redgiantaio.helpers.ApiInterfaceSweep;
import com.keredgiantaio.techsavanna.redgiantaio.methods.Sweep;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    ApiInterfaceSweep apiInterface;
    ProgressBar progressBar;
    private List<Sweep> structureList;
    SweepSearchAdapter adapter;
    ListView listview ;
    SearchView sv;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchStructures("type", "");

        progressBar = findViewById(R.id.prograss);

        // sv.setQueryHint("Search..");

    }



    public void fetchStructures(String type, String key){
        apiInterface = ApiClientSweep.getApiSweep().create(ApiInterfaceSweep.class);
        Call<List<Sweep>> call = apiInterface.getSweep(type, key);

        call.enqueue(new Callback<List<Sweep>>() {
            @Override
            public void onResponse(Call<List<Sweep>> call, Response<List<Sweep>> response) {

                System.out.println("Print"+response.body());
                progressBar.setVisibility(View.GONE);
                progressBar.setBackgroundColor(Color.BLUE);
                structureList = response.body();
                adapter = new SweepSearchAdapter(structureList, DashboardActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


//                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Object o = adapterView.getItemAtPosition(i);
//                        String str_text = o.toString();
//
//                        System.out.println("text selected"+str_text);
//                       // PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("sitename", str_text).apply();
//
//                        dismiss();
//                    }
//                });
            }

            @Override
            public void onFailure(Call<List<Sweep>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(DashboardActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //System.out.print("Errot new ::"+query);
                fetchStructures("type", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchStructures("type", newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...

                DashboardActivity.this.finish();
                System.exit(0);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

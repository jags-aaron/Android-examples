package com.neko.androidexamples.recycler_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.neko.androidexamples.R;
import com.neko.androidexamples.list_view.Model;

import java.util.ArrayList;

/**
 * This class shows how to implement a List with RecyclerView and CardView components
 * These dependencies must be added in App build.gradle :
 * - 'com.android.support:recyclerview-v7:26.+'
 * - 'com.android.support:cardview-v7:26.+'
 *
 * @link https://developer.android.com/training/material/lists-cards.html
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Model> dataToBePopulated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        dataToBePopulated = getDummyData();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        adapter = new RecyclerViewAdapter(dataToBePopulated, onDeleteButtonClickedListener);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * Callback for Delete Button
     * @see RecyclerViewAdapter
     */
    RecyclerViewAdapter.OnDeleteButtonClickedListener onDeleteButtonClickedListener
            = new RecyclerViewAdapter.OnDeleteButtonClickedListener() {
        @Override
        public void onDeleteClicked(int position) {
            removeItem(position);
        }
    };

    /**
     * Removes item at current position
     *
     * @param position
     */
    private void removeItem(int position) {
        // Removing current item
        dataToBePopulated.remove(position);
        //adapter.notifyDataSetChanged(); // it doesn't show animation
        // Notifying with animation
        adapter.notifyItemRemoved(position);
    }

    /**
     * Adds new Item to the list
     */
    private void addNewItem() {
        dataToBePopulated.add(new Model(
                R.mipmap.ic_launcher_round, "New Name", "New Description"));
        //adapter.notifyDataSetChanged(); // it doesn't show animation
        // Notifying with animation
        adapter.notifyItemInserted(dataToBePopulated.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                addNewItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Retrieve dummy data to be populated into ListView
     *
     * @return List of Models
     */
    private ArrayList<Model> getDummyData() {
        ArrayList<Model> dummyModels = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            dummyModels.add(new Model(
                    R.mipmap.ic_launcher_round, "Name: " + i, "Description: " + i));
        }

        return dummyModels;
    }
}

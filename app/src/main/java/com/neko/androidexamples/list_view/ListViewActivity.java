package com.neko.androidexamples.list_view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.neko.androidexamples.BaseActivity;
import com.neko.androidexamples.R;

import java.util.ArrayList;

public class ListViewActivity extends BaseActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private ArrayList<Model> dataToBePopulated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        findingViews();
        initComponents();
    }

    /**
     * Finds the views inside the xml
     */
    private void findingViews(){
        listView = (ListView) findViewById(R.id.listView);
    }

    /**
     * init all the components found
     */
    private void initComponents(){
        dataToBePopulated = getDummyData();
        adapter = new ListViewAdapter(dataToBePopulated, this, onDeleteButtonClickedListener);
        listView.setAdapter(adapter);
    }

    /**
     * Called when the user clicks on Delete Button
     * @see ListViewAdapter (OnListViewDeleteButtonClicked)
     */
    ListViewAdapter.OnListViewDeleteButtonClicked onDeleteButtonClickedListener =
            new ListViewAdapter.OnListViewDeleteButtonClicked() {
        @Override
        public void onDeleteButtonClicked(int position) {
            removeItem(position);
        }
    };

    /**
     * Removes item at current position
     * @param position
     */
    private void removeItem(int position){
        // Removing current item
        dataToBePopulated.remove(position);
        // Notifying adapter about the change
        adapter.notifyDataSetChanged();
    }

    /**
     * Adds new Item to the list
     */
    private void addNewItem(){
        dataToBePopulated.add(new Model(
                R.mipmap.ic_launcher_round, "New Name", "New Description"));
        adapter.notifyDataSetChanged();
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
     * @return List of Models
     */
    private ArrayList<Model> getDummyData(){
        ArrayList<Model> dummyModels = new ArrayList<>();

        for(int i=0; i<5; i++){
            dummyModels.add(new Model(
                    R.mipmap.ic_launcher_round, "Name: " + i, "Description: " + i));
        }

        return dummyModels;
    }

}
